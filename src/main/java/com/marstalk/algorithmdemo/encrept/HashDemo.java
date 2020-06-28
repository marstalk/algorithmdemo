package com.marstalk.algorithmdemo.encrept;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
        byte[] result = md5.digest();
        System.out.println(result);
        //[B@27973e9b
        System.out.println(new BigInteger(1, result).toString(16));
        //68e109f0f40ca72a15e05cc22786f8e6

        System.out.println("HelloWorld".hashCode());
        //439329280

        byte a = 1;
        byte b = -128;
        System.out.println(2*2*2*2*2*2*2*2);
        byte[] as = new byte[]{33, 127, -128, 33, 0};


    }

}
