// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.algorithm;

import com.github.bomberjin.utils.cipher.exception.EncryptyException;
import com.github.bomberjin.utils.cipher.util.Base64;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

public class RSAEncrypt extends AbstractEncrypt
{
    private String sKey;
    
    @Override
    protected String getAligorithm() {
        return "RSA";
    }
    
    @Override
    public String encrypty(String src) {
        if (this.encrypt != null) {
            src = this.encrypt.encrypty(src);
        }
        try {
            final Cipher cipher = Cipher.getInstance(this.getAligorithm());
            cipher.init(1, this.getKey(Base64.decodeBase64(this.sKey.getBytes())));
            final byte[] clearData = src.getBytes(this.encoding);
            final byte[] secData = new byte[((clearData.length - 1) / 117 + 1) * 128];
            int m = 0;
            int n = 0;
            while (clearData.length - m > 117) {
                n += cipher.doFinal(clearData, m, 117, secData, n);
                m += 117;
            }
            cipher.doFinal(clearData, m, clearData.length - m, secData, n);
            return new String(Base64.encodeBase64(secData));
        }
        catch (Exception e) {
            throw new EncryptyException(e);
        }
    }
    
    @Override
    protected Key getKey(final byte[] key) throws Exception {
        final KeyFactory keyFactory = KeyFactory.getInstance(this.getAligorithm());
        final X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(key);
        return keyFactory.generatePublic(bobPubKeySpec);
    }
    
    @Override
    protected String appendResult(final byte[] key) {
        return "";
    }
    
    @Override
    protected byte[] getKeyByte() throws Exception {
        return Base64.decodeBase64(this.sKey.getBytes());
    }
    
    public RSAEncrypt() {
    }
    
    public RSAEncrypt(final String key, final Encrypt encrypt) {
        super(encrypt);
        this.encoding = "UTF-8";
        this.sKey = key;
    }
    
    public RSAEncrypt(final String key, final String encoding, final Encrypt encrypt) {
        super(encoding, encrypt);
        this.sKey = key;
    }
    
    public RSAEncrypt(final String key) {
        this.encoding = "UTF-8";
        this.sKey = key;
    }
}
