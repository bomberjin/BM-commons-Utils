// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.algorithm;

import com.github.bomberjin.utils.cipher.exception.DecryptyException;
import com.github.bomberjin.utils.cipher.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public abstract class AbstractDecrypt implements Decrypt
{
    protected String key;
    protected String encoding;
    protected Decrypt decrypt;
    
    protected abstract String getAligorithm();
    
    @Override
    public String decrypty(String src) throws DecryptyException {
        final String aligor = this.getAligorithm();
        if (this.decrypt != null) {
            src = this.decrypt.decrypty(src);
        }
        final String[] sp = src.split(",");
        if ((this.key == null || "".equals(this.key)) && sp.length > 1) {
            this.key = sp[1];
            src = sp[0];
        }
        try {
            final Cipher cipher = Cipher.getInstance(aligor);
            cipher.init(2, this.getKey());
            return new String(cipher.doFinal(Base64.decodeBase64(src.getBytes())), this.encoding);
        }
        catch (Exception e) {
            throw new DecryptyException(e);
        }
    }
    
    public Key getKey() throws Exception {
        return new SecretKeySpec(Base64.decodeBase64(this.key.getBytes()), this.getAligorithm());
    }
    
    public AbstractDecrypt(final String encoding, final String key) {
        this.encoding = encoding;
        this.key = key;
    }
    
    public AbstractDecrypt(final String key) {
        this.key = key;
        this.encoding = "UTF-8";
    }
    
    public AbstractDecrypt(final String key, final String encoding, final Decrypt decrypt) {
        this.key = key;
        this.encoding = encoding;
        this.decrypt = decrypt;
    }
    
    public AbstractDecrypt(final String key, final Decrypt decrypt) {
        this.key = key;
        this.decrypt = decrypt;
        this.encoding = "UTF-8";
    }
}
