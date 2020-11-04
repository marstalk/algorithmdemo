package com.marstalk.io.nio.filereceiver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;

/**
 * 服务端，
 * 往客户端发送abc.txt文件之后，关闭连接。
 */
public class FileServer {
    public static void main(String[] args) throws IOException {
        FileServer fs = new FileServer();
        fs.start(9009);
    }

    public void start(int port) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        while (true) {
            SocketChannel socketChannel = ssc.accept();
            writeToSocketChannel(socketChannel);
        }
    }

    private void writeToSocketChannel(SocketChannel socketChannel) {
        if (null == socketChannel) {
            return;
        }
        System.out.println("accept client, start to send file");
        try (FileChannel abcFile = FileChannel.open(Paths.get("/Users/louisliu/IdeaProjects/algorithmdemo/src/main/java/com/marstalk/io/nio/filereceiver/abc.txt"))) {
            abcFile.transferTo(0, abcFile.size(), socketChannel);
            //在SocketChannel传输通道关闭前，尽量发送一个输出结束标志到对方端。
            socketChannel.shutdownOutput();
            socketChannel.close();
            System.out.println("send file successfully, close channel and close socket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
