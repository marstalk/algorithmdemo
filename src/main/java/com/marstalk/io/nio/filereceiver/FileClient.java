package com.marstalk.io.nio.filereceiver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 客户端
 * 连接上服务器，并且接受文件，写到本地。
 */
public class FileClient {
    private static final String destFile = "/Users/louisliu/IdeaProjects/algorithmdemo/src/main/java/com/marstalk/io/nio/filereceiver/abc-received.txt";

    public static void main(String[] args) throws IOException {
        FileClient fc = new FileClient();
        fc.connect("localhost", 9009);
    }

    public void connect(String hostName, int port) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(hostName, port));
        receiveFile(sc);
    }

    private void receiveFile(SocketChannel sc) {
        try (FileChannel fc = FileChannel.open(Paths.get(destFile), StandardOpenOption.WRITE)) {
            //因为无法知道服务器端发送过来多少数据，所以transferFrom无法使用。
            //fc.transferFrom(sc, 0, 2048);
            //所以使用了while循环，直到数据结束。
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (sc.read(byteBuffer) > 0) {
                //position回到起始位置，
                byteBuffer.flip();
                //从position位置开始，全部写到channel中
                fc.write(byteBuffer);
                //写完之后，清空byteBuffer，接着下一个循环。
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
