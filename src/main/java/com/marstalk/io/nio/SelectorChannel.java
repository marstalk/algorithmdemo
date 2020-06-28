package com.marstalk.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class SelectorChannel {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey selectionKey : selectionKeys) {
                SocketChannel channel = (SocketChannel)selectionKey.channel();
                if (selectionKey.isAcceptable()) {
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    System.out.println();
                }
            }
        }

    }

}
