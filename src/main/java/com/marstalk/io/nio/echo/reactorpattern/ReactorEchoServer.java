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

/**
 * Reactor模式的单线程版本 https://www.jianshu.com/p/b5cf39bc09c2
 *
 * @author shanzhonglaosou
 */
public class ReactorEchoServer implements Runnable {
    public static void main(String[] args) {
        new Thread(new ReactorEchoServer(9009), "reactor echo server").start();
    }

    private Selector selector;
    private ServerSocketChannel ssc;

    public ReactorEchoServer(int port) {
        try {
            // open channel
            ssc = ServerSocketChannel.open();
            // listen to port
            ssc.socket().bind(new InetSocketAddress(port));
            // non-blocking mode
            ssc.configureBlocking(false);
            // listen accept event, so whenever accpet event happen, the corresponding sk can get acceptorHandler
            selector = Selector.open();
            //SelectionKey对象是，ssc对应的每一个accept事件都是这个对象
            SelectionKey sk = ssc.register(selector, SelectionKey.OP_ACCEPT);
            sk.attach(new AcceptorHandler(ssc, selector));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //忘了这一步，导致出bug了。
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    dispatch(selectionKey);
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分发，让对应的Handler来处理对应的事件。因为不同的selectionKey的attachment拿到的runnable是不同的实现类。
     * 如果是accept事件，拿到的是AcceptorHandler<br/>
     * 如果是readable事件，拿到的是IOHandler
     *
     * @param selectionKey
     */
    private void dispatch(SelectionKey selectionKey) {
        Runnable runnable = (Runnable) selectionKey.attachment();
        System.out.println("dispatch: " + runnable);
        if (runnable != null) {
            runnable.run();
        }
    }

    static class AcceptorHandler implements Runnable {
        private ServerSocketChannel ssc;
        private Selector selector;

        public AcceptorHandler(ServerSocketChannel ssc, Selector selector) {
            System.out.println("new Acceptor");
            this.ssc = ssc;
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                System.out.println("serverSocketChannel.accept()");
                SocketChannel socketChannel = ssc.accept();
                if (socketChannel != null) {
                    //拿到客户端连接的SocketChannel，然后注册readable事件。
                    //每一个客户端对应一个EchoHandler实例。
                    new EchoHandler(socketChannel, selector);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "AcceptorHandler{" +
                    "selector=" + selector +
                    '}';
        }
    }

    static class EchoHandler implements Runnable {
        private SocketChannel socketChannel;
        private SelectionKey sk;
        private static final int RECEIVING = 0, SENDING = 1;
        private int state = RECEIVING;
        private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        public EchoHandler(SocketChannel socketChannel, Selector selector) {
            System.out.println("new EchoHandler()");
            this.socketChannel = socketChannel;
            try {
                socketChannel.configureBlocking(false);
                sk = socketChannel.register(selector, 0);
                sk.attach(this);
                // TODO what does it do?? interestOps and wakeup
                //关注readable事件。
                sk.interestOps(SelectionKey.OP_READ);
                selector.wakeup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "EchoHandler{" +
                    "state=" + state +
                    '}';
        }

        @Override
        public void run() {
            //TODO 为什么搞明白为什么执行完读之后，会再触发一个writable事件。
            try {
                System.out.println("state: " + state);
                if (state == RECEIVING) {
                    System.out.println("receiving");
                    //第一次肯定是这个里，接收数据。
                    int length;
                    while ((length = socketChannel.read(byteBuffer)) > 0) {
                        System.out.println(new String(byteBuffer.array(), 0, length));
                    }
                    byteBuffer.flip();
                    //改为关注可写事件。
                    sk.interestOps(SelectionKey.OP_WRITE);
                    state = SENDING;
                } else if (state == SENDING) {
                    System.out.println("sending");
                    //发送
                    socketChannel.write(byteBuffer);
                    //清除byteBuffer
                    byteBuffer.clear();
                    //注册读事件
                    sk.interestOps(SelectionKey.OP_READ);
                    state = RECEIVING;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
