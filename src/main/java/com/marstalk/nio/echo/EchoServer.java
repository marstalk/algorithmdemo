package com.marstalk.nio.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 写一个EchoServer。
 */
public class EchoServer {

    public static void main(String[] args) throws IOException {

        //使用事件驱动模式，
        Selector selector = Selector.open();

        //每个端口对应一个ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket socket = ssc.socket();
        socket.bind(new InetSocketAddress(9999));

        SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            //查询有没有关心的事件发生，【阻塞的】
            int select = selector.select();

            //获取到所有的事件集合，走到这里，说明已经
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {

                    //key对应的accept时间，那么key.channel拿到的就是serverSocketChannel。
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();

                    //调用accept方法获取socketChannel，因为一定会拿到，即【非阻塞】。
                    SocketChannel sc = channel.accept();

                    if (sc != null) {
                        //将socketChannel（某个客户端）设置为非阻塞的
                        sc.configureBlocking(false);

                        //关注这个socketChannel的可读事件，即客户端发送过来消息。
                        sc.register(selector, SelectionKey.OP_READ);
                    }
                }

                if (key.isReadable()) {
                    //key对应的readable事件，
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                }
            }
        }
    }
}
