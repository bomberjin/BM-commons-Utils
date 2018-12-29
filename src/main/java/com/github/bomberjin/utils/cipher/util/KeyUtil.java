// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.util;

import sun.misc.BASE64Encoder;

import java.io.RandomAccessFile;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class KeyUtil
{
    public static final String CHARSET_ENCODING = "UTF-8";
    private static final BASE64Encoder base64Encoder;
    
    public static void genKeyPairFiles(final String filePath) {
        try {
            final KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(1024);
            final KeyPair keyPair = keyPairGen.generateKeyPair();
            final RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
            final RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
            final byte[] publicKeyByte = publicKey.getEncoded();
            final byte[] privateKeyByte = privateKey.getEncoded();
            final RandomAccessFile publicKeyFile = new RandomAccessFile(filePath + "public.key", "rw");
            publicKeyFile.write(KeyUtil.base64Encoder.encodeBuffer(publicKeyByte).getBytes("UTF-8"));
            publicKeyFile.close();
            final RandomAccessFile privateKeyFile = new RandomAccessFile(filePath + "private.key", "rw");
            privateKeyFile.write(KeyUtil.base64Encoder.encodeBuffer(privateKeyByte).getBytes("UTF-8"));
            privateKeyFile.close();
        }
        catch (Exception e) {
            throw new RuntimeException("\u751f\u6210\u6210\u5bf9\u5bc6\u94a5\u5931\u8d25", e);
        }
    }
    
    static {
        base64Encoder = new BASE64Encoder();
    }
}
