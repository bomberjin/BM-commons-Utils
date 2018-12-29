// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher;

import com.github.bomberjin.utils.cipher.algorithm.*;

import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class CipherUtil
{
    public static final String CHARSET_ENCODING = "UTF-8";
    
    public static String encryptCipher(final String dataContent, final String pubicKeyPath, final String privateKeyPath) {
        String encyptStr = "";
        try {
            final DESEncrypt desEncrypt = new DESEncrypt();
            final String desEncryptStr = desEncrypt.encrypty(dataContent);
            final RSAEncrypt rsaEncrypt = new RSAEncrypt(getKey(pubicKeyPath));
            final String rsaEncryptStr = rsaEncrypt.encrypty(desEncryptStr);
            final SignatureEncrypt signatureEncypt = new SignatureEncrypt(getKey(privateKeyPath));
            encyptStr = signatureEncypt.encrypty(rsaEncryptStr);
            encyptStr = URLEncoder.encode(encyptStr, "UTF-8");
        }
        catch (Exception e) {
            throw new RuntimeException("\u52a0\u5bc6\u5931\u8d25", e);
        }
        return encyptStr;
    }
    
    public static String decryptCipher(String encryptDataContent, final String pubicKeyPath, final String privateKeyPath) {
        String decryptStr = "";
        try {
            encryptDataContent = URLDecoder.decode(encryptDataContent, "UTF-8");
            final SignatureDecrypt signaturedecypt = new SignatureDecrypt(getKey(pubicKeyPath), encryptDataContent.split(",")[1]);
            final String signaturedecyptStr = signaturedecypt.decrypty(encryptDataContent.split(",")[0]);
            final RSADecrypt rsaDecrypt = new RSADecrypt(getKey(privateKeyPath));
            final String rsaDecryptStr = rsaDecrypt.decrypty(signaturedecyptStr);
            final DESDecrypt desDecrypt = new DESDecrypt();
            decryptStr = desDecrypt.decrypty(rsaDecryptStr);
        }
        catch (Exception e) {
            throw new RuntimeException("\u89e3\u5bc6\u5931\u8d25", e);
        }
        return decryptStr;
    }
    
    private static String getKey(final String keyFilepath) {
        String keyString = "";
        try {
            final RandomAccessFile keyFile = new RandomAccessFile(keyFilepath, "r");
            String tempStr = null;
            while ((tempStr = keyFile.readLine()) != null) {
                keyString += tempStr;
            }
            keyFile.close();
            keyString = keyString.replace("\r\n", "").replace("\n", "");
        }
        catch (Exception e) {
            throw new RuntimeException("\u8bfb\u53d6\u5bc6\u94a5\u6587\u4ef6\u3010" + keyFilepath + "\u3011\u5931\u8d25", e);
        }
        return keyString;
    }
    
    public static final String MD5(final String srcStr) {
        final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            final byte[] btInput = srcStr.getBytes("UTF-8");
            final MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            final byte[] md = mdInst.digest();
            final int j = md.length;
            final char[] str = new char[j * 2];
            int k = 0;
            for (final byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xF];
                str[k++] = hexDigits[byte0 & 0xF];
            }
            return new String(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

}
