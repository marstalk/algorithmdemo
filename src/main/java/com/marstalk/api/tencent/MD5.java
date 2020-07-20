package com.marstalk.api.tencent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String getMD5(String s){
        char hexDigits[] ={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try{
            byte[] btInput = s.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(btInput);
            byte[] md = md5.digest();
            int j = md.length;
            char chars[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++){
                byte byte0 = md[i];
                chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
                chars[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
