package com.marstalk.io.nio.discardserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * 服务器端，接受到消息只做打印。
 * 使用多路复用：selector
 *
 * @author shanzhonglaosou
 */
public class DiscardServerNIO {
    public static void main(String[] args) throws IOException {
        DiscardServerNIO discardServerNIO = new DiscardServerNIO();
        discardServerNIO.start(9009);
    }

    public void start(int port) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isAcceptable()) {
                    System.out.println("acceptable event happen");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel sc = serverSocketChannel.accept();
                    if (null != sc) {
                        //注意判空
                        System.out.println("client accepted");
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        System.out.println("register readable event");
                    }
                }

                if (selectionKey.isReadable()) {
                    System.out.println("readable event happen");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    StringBuilder sb = new StringBuilder();
                    while (channel.read(byteBuffer) > 0) {
                        byte[] bytes = new byte[byteBuffer.position()];
                        byteBuffer.flip();
                        byteBuffer.get(bytes, 0, bytes.length);
                        byteBuffer.clear();
                        String s = new String(bytes, StandardCharsets.UTF_8);
                        sb.append(s);
                    }
                    System.out.println(sb.toString());
                }
            }
        }
    }

}
