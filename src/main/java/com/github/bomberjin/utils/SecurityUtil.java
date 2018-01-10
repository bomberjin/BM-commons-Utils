package com.github.bomberjin.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    /**
     * 32 位md5 加密
     * @param plainText
     * @return
     * @throws NoSuchAlgorithmException
     */
    public final static String Md5With32(String plainText ) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0) i+= 256;
                if(i<16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString().trim();

    }

    /**
     * 16 位Md5 加密
     * @param plainText
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String Md5With16(String plainText) throws NoSuchAlgorithmException {
        String result = null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().substring(8, 24);

        return result;
    }


    /**
     * base 64 解密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] BASE64Decrypt(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * base 64 加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String BASE64Encrypt(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }


}
