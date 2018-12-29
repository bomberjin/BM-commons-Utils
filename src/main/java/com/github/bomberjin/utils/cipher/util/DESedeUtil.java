// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.util;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DESedeUtil
{
    private static String Algorithm;
    
    public static SecretKey getKey(final String key) {
        SecretKey deskey = null;
        try {
            KeyGenerator gen = KeyGenerator.getInstance(DESedeUtil.Algorithm);
            final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            gen.init(112, secureRandom);
            deskey = gen.generateKey();
            gen = null;
            return deskey;
        }
        catch (NoSuchAlgorithmException e) {
            e.toString();
            return deskey;
        }
    }
    
    public static String decrypt(final String key, final String src) {
        final SecretKey deskey = getKey(key);
        String sourceXml = null;
        try {
            final Cipher c2 = Cipher.getInstance(DESedeUtil.Algorithm + "/ECB/PKCS5Padding");
            c2.init(2, deskey);
            sourceXml = new String(c2.doFinal(Base64.decodeBase64(src.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.toString();
        }
        return sourceXml;
    }
    
    public static String encode(final String key, final String src) {
        final SecretKey deskey = getKey(key);
        String destCode = null;
        try {
            final Cipher c1 = Cipher.getInstance(DESedeUtil.Algorithm + "/ECB/PKCS5Padding");
            c1.init(1, deskey);
            final byte[] encoded = c1.doFinal(src.getBytes("UTF-8"));
            destCode = new String(Base64.encodeBase64(encoded));
        }
        catch (Exception e) {
            e.toString();
        }
        return destCode;
    }
    
//    public static void main(final String[] args) {
//        final String key = "jljksaldkfjslafjsdfdf";
//        final String szSrc = "afdfdssdf";
//        System.out.println("\u52a0\u5bc6\u524d\u7684\u5b57\u7b26\u4e32:\n" + szSrc);
//        final String code = encode(key, szSrc);
//        System.out.println("\u52a0\u5bc6\u540e\u7684\u5b57\u7b26\u4e32:\n" + code);
//        System.out.println("\u89e3\u5bc6\u540e\u7684\u5b57\u7b26\u4e32:\n" + decrypt(key, code));
//    }
    
    static {

        DESedeUtil.Algorithm = "DESede";
    }
}
