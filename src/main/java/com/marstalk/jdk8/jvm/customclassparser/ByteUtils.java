package com.marstalk.jdk8.jvm.customclassparser;

/**
 * Specific Utils for Class file format<br>
 * Higher bits means smaller index
 *
 * @author shanzhoulaozou
 */
public class ByteUtils {

    /**
     * @param bytes
     * @return
     */
    public static short u1Short(byte[] bytes) {
        int i = bytes[0];
        return (short) i;
    }

    /**
     * @param bytes
     * @return
     */
    public static short u2Short(byte[] bytes) {
        int i = bytes[0] << 8 | bytes[1];
        return (short) i;
    }

    public static int u4Int(byte[] bytes) {
        return bytes[0] << 24 | bytes[1] << 16 | bytes[2] << 8 | bytes[3];
    }

    public static long u8Long(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < bytes.length; i++) {
            value += (value << 8) + (bytes[i] & 0xff);
        }
        return value;
    }

    /**
     * integer to human readable binary
     * @param i
     * @return
     */
    public static String int2BinaryStr(int i) {
        return recursive(i);
    }

    private static String recursive(int i) {
        if (i == 1 || i == 0) {
            return i + "";
        }
        int j = i % 2;
        return recursive((i - j) / 2) + j;
    }
}
