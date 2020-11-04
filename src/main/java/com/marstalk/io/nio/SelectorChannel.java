package com.marstalk.io.nio;

import io.netty.buffer.ByteBuf;
import org.apache.ibatis.annotations.SelectKey;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 为了证明一个socketChannel在连接上之后，会产生两个事件，readable 和 writable
 *
 * @author shanzhonglaosou
 */
public class SelectorChannel {

    public static void main(String[] args) throws IOException {
        new SelectorChannel().start();
    }

    private static int socketChannelId = 0;

    public void start() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach("serverSocketChannel");
        while (!Thread.interrupted() && selector.select() > 0) {
            //看到有代码是用上面的方式来写的，多一个>0的判断，但是根据方法描述，这个方法是阻塞的，方法返回时，至少有一个channel发生了事件（可能发生了多个事件）已经发送了，即select()>0
            //selector.select();
            //selector.selectNow(); 不阻塞，即可返回，可能等于0
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                System.out.println(selectionKey.readyOps());
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    System.out.println(selectionKey.attachment() + " accept event");
                    ssc.configureBlocking(false);
                    System.out.println("socketChannel interest readable and writable event");
                    SocketChannel socketChannel = ssc.accept();
                    if (socketChannel != null) {
                        System.out.println("socketChannel is not null " + socketChannel);
                        socketChannel.configureBlocking(false);
                        SelectionKey sk2 = socketChannel.register(selector, SelectionKey.OP_READ);
                        sk2.attach("socketChannel " + socketChannelId++);
                    } else {
                        //TODO 什么情况是null
                        System.out.println("socketChannel is null");
                    }
                }

                if (selectionKey.isReadable()) {
                    System.out.println(selectionKey.attachment() + " ready for read");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length;
                    while ((length = channel.read(byteBuffer)) > 0) {
                        System.out.println(">>>> " + new String(byteBuffer.array(), 0, length));
                    }
                }

                if (selectionKey.isWritable()) {
                    System.out.println(selectionKey.attachment() + " ready for write");
                }
                //you can per loop
                keyIterator.remove();
            }
            // or clear all the keys once after loop
            selectionKeys.clear();
        }
    }

}
