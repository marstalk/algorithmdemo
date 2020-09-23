package com.marstalk.io.nio.echo.reactorpattern;

import java.io.IOException;
import java.net.InetSocketAddress;
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

    private int port;
    private Selector selector;
    private ServerSocketChannel ssc;

    public ReactorEchoServer(int port) {
        this.port = port;
        try {
            // open channel
            ssc = ServerSocketChannel.open();
            // listen to port
            ssc.bind(new InetSocketAddress(port));
            // non-blocking mode
            ssc.configureBlocking(false);
            // listen accept event, so whenever accpet event happen, the corresponding selectionKey can get acceptorHandler
            SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new AcceptorHandler(ssc, selector));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class AcceptorHandler implements Runnable {
        private ServerSocketChannel ssc;
        private Selector selector;

        public AcceptorHandler(ServerSocketChannel ssc, Selector selector) {
            this.ssc = ssc;
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = ssc.accept();
                if (socketChannel != null) {
                    new EchoHandler(socketChannel, selector);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class EchoHandler implements Runnable{
        private SocketChannel socketChannel;
        private Selector selector;

        public EchoHandler(SocketChannel socketChannel, Selector selector) {
            this.socketChannel = socketChannel;
            this.selector = selector;
            try {
                socketChannel.configureBlocking(false);
                SelectionKey sk = socketChannel.register(selector, 0);
                sk.attach(this);
                // TODO what does it do?? interestOps and wakeup
                sk.interestOps(SelectionKey.OP_READ);
                selector.wakeup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                dispatch(selectionKey);
            }
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable runnable = (Runnable) selectionKey.attachment();
        runnable.run();
    }

}
