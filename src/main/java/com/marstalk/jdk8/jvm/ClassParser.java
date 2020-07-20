package com.marstalk.jdk8.jvm;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 解析class文件
 */
public class ClassParser {
    private static String hexStr = "0123456789abcdef";

    public static void main(String[] args) throws IOException {
        ClassParser cp = new ClassParser();
        String path = "/Users/louisliu/IdeaProjects/algorithmdemo/target/classes/com/marstalk/jdk8/jvm/Test2.class";
        ClassFile classFile = cp.parse(path);
        System.out.println(classFile);
    }

    public ClassFile parse(String classFilePath){
        ClassFile cf = new ClassFile();

        try (FileChannel fileChannel = FileChannel.open(Paths.get(classFilePath), StandardOpenOption.READ)) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();

            //magic number: u4
            byte[] bytes = new byte[4];
            byteBuffer.get(bytes);
            String magicStr = byte2HexStr(bytes);
            if ("cafe babe".equalsIgnoreCase(magicStr)) {
                System.out.println("This is not a class file " + classFilePath);
                return null;
            }
            int magicNum = getInt(bytes, 0);
            cf.setMagic(magicNum);


            //minor version u2
            bytes = new byte[2];
            byteBuffer.get(bytes);
            short s = getShort(bytes, 0);
            cf.setMinorVersion(s);

            //major version
            bytes = new byte[2];
            byteBuffer.get(bytes);
            s = getShort(bytes, 0);
            cf.setMajorVersion(s);



            byteBuffer.clear();
            return cf;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int byte2Int(byte[] bytes){
        String s = byte2HexStr(bytes);
        return Integer.parseInt(s, 16);
    }

    /**
     * 四个字节
     * @param bb
     * @param index
     * @return
     */
    public static int getInt(byte[] bb, int index) {
        return (int) ((((bb[index + 3] & 0xff) << 24)
                | ((bb[index + 2] & 0xff) << 16)
                | ((bb[index + 1] & 0xff) << 8) | ((bb[index + 0] & 0xff) << 0)));
    }

        /**
         * 两个字节
         * @param b
         * @param index
         * @return
         */
    public short getShort(byte[] b, int index) {
        return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
    }


    private String byte2HexStr(byte[] bytes) {

        String result = "";
        String hex = "";
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            result += hex;
        }
        return result;
    }
}
