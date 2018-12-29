// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.algorithm;



import com.github.bomberjin.utils.cipher.exception.EncryptyException;
import com.github.bomberjin.utils.cipher.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public abstract class AbstractEncrypt implements Encrypt
{
    protected String encoding;
    protected Encrypt encrypt;
    
    protected abstract String getAligorithm();
    
    @Override
    public String encrypty(String src) {
        try {
            final String aligor = this.getAligorithm();
            if (this.encrypt != null) {
                src = this.encrypt.encrypty(src);
            }
            final Cipher cipher = Cipher.getInstance(aligor);
            final byte[] key = this.getKeyByte();
            cipher.init(1, this.getKey(key));
            final byte[] b = src.getBytes(this.encoding);
            return new String(Base64.encodeBase64(cipher.doFinal(b)), this.encoding) + this.appendResult(key);
        }
        catch (Exception e) {
            throw new EncryptyException(e);
        }
    }
    
    protected String appendResult(final byte[] key) {
        return "," + new String(Base64.encodeBase64(key));
    }
    
    protected byte[] getKeyByte() throws Exception {
        final KeyGenerator generator = KeyGenerator.getInstance(this.getAligorithm());
        final SecretKey skey = generator.generateKey();
        return skey.getEncoded();
    }
    
    protected Key getKey(final byte[] key) throws Exception {
        return new SecretKeySpec(key, this.getAligorithm());
    }
    
    public AbstractEncrypt(final String encoding) {
        this.encoding = encoding;
    }
    
    public AbstractEncrypt(final String encoding, final Encrypt encrypt) {
        this.encoding = encoding;
        this.encrypt = encrypt;
    }
    
    public AbstractEncrypt(final Encrypt encrypt) {
        this();
        this.encrypt = encrypt;
    }
    
    public AbstractEncrypt() {
        this.encoding = "UTF-8";
    }
}
