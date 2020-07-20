package com.marstalk.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIODemo1 {

    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get("D:\\test.txt"), StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        //step1
        int read = fileChannel.read(byteBuffer);
        while (read != -1) {
            System.out.println(read);
            //step2
            byteBuffer.flip();
            byte[] b = new byte[byteBuffer.limit()];
            //step3
            byteBuffer.get(b);
            System.out.println(new String(b, 0, b.length));
            //step4
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        fileChannel.close();
    }
}
