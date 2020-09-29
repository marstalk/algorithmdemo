package com.marstalk.io.nio.echo.reactorpattern;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Echo服务器，
 * reactor模式的多线程版本，生产中一般采用这个模式。
 * 1，多个子响应器，其中一个子响应器除了分发socketChannel外，还有监听ServerSocketChannel的accept事件。
 * 2，分发socketChannel事件到线程池中。
 *
 * @author shanzhonglaosou
 */
public class MultiThreadReactorEchoServer {
    public static void main(String[] args) {
        new MultiThreadReactorEchoServer(9999).startService();
    }

    private Selector[] selectors = new Selector[2];
    private SubRector[] subRectors = new SubRector[2];
    private AtomicInteger next = new AtomicInteger(0);
    private ServerSocketChannel ssc;

    public MultiThreadReactorEchoServer(int port) {
        try {
            System.out.println("new MultiThreadReactorEchoServer");
            selectors[0] = Selector.open();
            selectors[1] = Selector.open();

            //只需要一个subRector负责分监听、分发ssc
            ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(port));
            ssc.configureBlocking(false);
            SelectionKey sk = ssc.register(selectors[0], SelectionKey.OP_ACCEPT);
            sk.attach(new AcceptorHandler());

            subRectors[0] = new SubRector(selectors[0]);
            subRectors[1] = new SubRector(selectors[1]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startService() {
        System.out.println("start service, start two thread");
        new Thread(subRectors[0], "subReactor-thread-0").start();
        new Thread(subRectors[1], "subReactor-thread-1").start();
    }

    private static class SubRector implements Runnable {
        private Selector selector;

        public SubRector(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    selector.select();
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (null != selectionKey) {
                            dispatch(selectionKey);
                        }
                    }
                    keySet.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void dispatch(SelectionKey selectionKey) {
            Runnable handler = (Runnable) selectionKey.attachment();
            if (handler != null) {
                handler.run();
            }
        }
    }


    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            System.out.println("acceptorHandler run");
            try {
                SocketChannel socketChannel = ssc.accept();
                if (socketChannel != null) {
                    new MultiThreadEchoHandler(socketChannel, selectors[next.get()]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (next.incrementAndGet() == selectors.length) {
                next.set(0);
            }
        }
    }
    static class MultiThreadEchoHandler implements Runnable {
        private static final int RECEIVING = 1, SENDING = 2;
        private SocketChannel sc;
        private int state = RECEIVING;
        private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        private SelectionKey sk;
        private static AtomicInteger ai = new AtomicInteger(0);
        /**
         * 静态的线程池
         */
        private static ExecutorService pool = new ThreadPoolExecutor(2, 2, 1000,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(50), r -> new Thread(r, "echoHandler-thread-" + ai.getAndIncrement()));

        public MultiThreadEchoHandler(SocketChannel sc, Selector selector) {
            System.out.println("new MultiThreadEchoHandler with selector: " + selector);
            this.sc = sc;
            try {
                sc.configureBlocking(false);
                sk = sc.register(selector, 0);
                //这里的this指的MultiThreadReactorEchoServer，并不是本multiThreadEchoHandler实例
                sk.attach(this);
                sk.interestOps(SelectionKey.OP_READ);
                //注册了一个新的channel并且关注了可读事件，即可唤醒selector。
                selector.wakeup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("submit asyncTask to pool");
            pool.execute(new MultiThreadEchoHandler.AsyncTask());
            //pool.submit(new AsyncTask());
        }

        class AsyncTask implements Runnable {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run asyncTask.run()");
                MultiThreadEchoHandler.this.runAsync();
            }
        }

        /**
         * 此方法需进行同步化，因为EchoHandler被绑定在SelectionKey上，而这个SelectionKey会被多个线程共享使用，
         * 如果不同步，那么byteBuffer, stat, sk都会乱掉
         */
        private synchronized void runAsync() {
            try {
                if (state == SENDING) {
                    System.out.println("sending");
                    sc.write(byteBuffer);
                    //写完之后，将byteBuffer清空。并且关注这个selectionKey的可读事件
                    byteBuffer.clear();
                    sk.interestOps(SelectionKey.OP_READ);
                    state = RECEIVING;
                } else if (state == RECEIVING) {
                    System.out.println("receiving");
                    int length = 0;
                    while ((length = sc.read(byteBuffer)) > 0) {
                        System.out.println(new String(byteBuffer.array(), 0, length));
                    }
                    //走到这里，说明已经读满byteBuffer或者已经读完所有的网卡数据。应该切换成写模式，并且关注这个selectionKey的可写事件。
                    byteBuffer.flip();
                    sk.interestOps(SelectionKey.OP_WRITE);
                    state = SENDING;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
