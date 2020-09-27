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
 *
 * Echo服务器，
 * reactor模式的多线程版本，生产中一般采用这个模式。
 * @author shanzhonglaosou
 */
public class ReactorEchoServerMultiThread {
    public static void main(String[] args) {
        new ReactorEchoServerMultiThread().start(9999);
    }

    public void start(int port){
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(port));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            SelectionKey sk = ssc.register(selector, SelectionKey.OP_ACCEPT);
            sk.attach(new AcceptorHandler(ssc, selector));
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (null != selectionKey) {
                        dispatch(selectionKey);
                    }
                }
                selectionKeys.clear();
            }
            ssc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable handler = (Runnable)selectionKey.attachment();
        handler.run();
    }

    static class AcceptorHandler implements Runnable{
        private ServerSocketChannel ssc;
        private Selector selector;

        public AcceptorHandler(ServerSocketChannel ssc, Selector selector) {
            this.ssc = ssc;
            this.selector = selector;
        }

        @Override
        public void run() {
            SocketChannel socketChannel = null;
            try {
                socketChannel = ssc.accept();
                if (socketChannel != null) {
                    new EchoHandler(socketChannel, selector);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class EchoHandler implements Runnable{
        private static final int RECEIVING=1, SENDING=2;
        private SocketChannel sc;
        private int state;
        private ByteBuffer byteBuffer;
        private SelectionKey sk;


        public EchoHandler(SocketChannel sc, Selector selector) {
            this.sc = sc;
            try {
                sc.configureBlocking(false);
                sk = sc.register(selector, SelectionKey.OP_READ);
                sk.attach(this);
                this.state = RECEIVING;
                //注册了一个新的channel并且关注了可读事件，即可唤醒selector。
                selector.wakeup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                if (state == RECEIVING) {
                    byteBuffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = sc.read(byteBuffer)) > 0) {
                        System.out.println(new String(byteBuffer.array(), 0, length));
                    }
                    //走到这里，说明已经读满byteBuffer或者已经读完所有的网卡数据。应该切换成写模式，并且关注这个selectionKey的可写事件。
                    byteBuffer.flip();
                    sk.interestOps(SelectionKey.OP_WRITE);
                    state = SENDING;
                } else if (state == SENDING) {
                    sc.write(byteBuffer);
                    //写完之后，将byteBuffer清空。并且关注这个selectionKey的可读事件
                    byteBuffer.clear();
                    sk.interestOps(SelectionKey.OP_READ);
                    state = RECEIVING;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
