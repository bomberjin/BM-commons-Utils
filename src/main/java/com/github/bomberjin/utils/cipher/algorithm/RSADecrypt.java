// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.algorithm;

import com.github.bomberjin.utils.cipher.exception.DecryptyException;
import com.github.bomberjin.utils.cipher.util.Base64;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSADecrypt extends AbstractDecrypt
{
    public RSADecrypt(final String key, final Decrypt decrypt) {
        super(key, decrypt);
    }
    
    public RSADecrypt(final String key, final String encoding, final Decrypt decrypt) {
        super(key, encoding, decrypt);
    }
    
    public RSADecrypt(final String encoding, final String key) {
        super(encoding, key);
    }
    
    public RSADecrypt(final String key) {
        super(key);
    }
    
    @Override
    public String decrypty(String src) throws DecryptyException {
        try {
            if (this.decrypt != null) {
                src = this.decrypt.decrypty(src);
            }
            final Cipher cipher = Cipher.getInstance(this.getAligorithm());
            cipher.init(2, this.getKey());
            final byte[] secData = Base64.decodeBase64(src.getBytes());
            final byte[] tmpData = new byte[secData.length];
            int m = 0;
            for (int n = 0; secData.length - n != 0; n += 128) {
                m += cipher.doFinal(secData, n, 128, tmpData, m);
            }
            return new String(tmpData, 0, m, this.encoding);
        }
        catch (Exception e) {
            throw new DecryptyException(e);
        }
    }
    
    @Override
    public Key getKey() throws Exception {
        final KeyFactory keyFactory = KeyFactory.getInstance(this.getAligorithm());
        final PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(this.key.getBytes()));
        return keyFactory.generatePrivate(priPKCS8);
    }
    
    @Override
    protected String getAligorithm() {
        return "RSA";
    }
}
