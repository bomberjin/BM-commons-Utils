// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.algorithm;

import com.github.bomberjin.utils.cipher.exception.EncryptyException;
import com.github.bomberjin.utils.cipher.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

public class SignatureEncrypt extends AbstractEncrypt
{
    private String key;
    
    @Override
    public String encrypty(String src) {
        try {
            if (this.encrypt != null) {
                src = this.encrypt.encrypty(src);
            }
            final byte[] srcByte = src.getBytes(this.encoding);
            final Signature signature = Signature.getInstance(this.getAligorithm());
            signature.initSign((PrivateKey)this.getKey());
            signature.update(srcByte);
            return src + "," + new String(Base64.encodeBase64(signature.sign()));
        }
        catch (Exception e) {
            throw new EncryptyException(e);
        }
    }
    
    protected Key getKey() throws Exception {
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        final PKCS8EncodedKeySpec bobPubKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(this.key.getBytes()));
        return keyFactory.generatePrivate(bobPubKeySpec);
    }
    
    @Override
    protected String getAligorithm() {
        return "MD5WithRSA";
    }
    
    public SignatureEncrypt(final String key, final Encrypt encrypt) {
        super(encrypt);
        this.key = key;
    }
    
    public SignatureEncrypt(final String key, final String encoding, final Encrypt encrypt) {
        super(encoding, encrypt);
        this.key = key;
    }
    
    public SignatureEncrypt(final String key) {
        this.encoding = "UTF-8";
        this.key = key;
    }
    
    public SignatureEncrypt(final String encoding, final String key) {
        this.encoding = encoding;
        this.key = key;
    }
}
