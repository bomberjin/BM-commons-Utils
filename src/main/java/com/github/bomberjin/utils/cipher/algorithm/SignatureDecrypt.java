// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.algorithm;

import com.github.bomberjin.utils.cipher.exception.DecryptyException;
import com.github.bomberjin.utils.cipher.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class SignatureDecrypt extends AbstractDecrypt
{
    private String sign;
    
    public SignatureDecrypt(final String key, final String sign, final Decrypt decrypt) {
        super(key, decrypt);
        this.sign = sign;
    }
    
    public SignatureDecrypt(final String key, final String sign, final String encoding, final Decrypt decrypt) {
        super(key, encoding, decrypt);
        this.sign = sign;
    }
    
    public SignatureDecrypt(final String encoding, final String key, final String sign) {
        super(encoding, key);
        this.sign = sign;
    }
    
    public SignatureDecrypt(final String key, final String sign) {
        super(key);
        this.sign = sign;
    }
    
    @Override
    public String decrypty(String src) throws DecryptyException {
        try {
            if (this.decrypt != null) {
                src = this.decrypt.decrypty(src);
            }
            final Signature signature = Signature.getInstance(this.getAligorithm());
            signature.initVerify((PublicKey)this.getKey());
            signature.update(src.getBytes(this.encoding));
            if (!signature.verify(Base64.decodeBase64(this.sign.getBytes()))) {
                throw new DecryptyException("\u7b7e\u540d\u9a8c\u8bc1\u5931\u8d25");
            }
            return src;
        }
        catch (Exception e) {
            throw new DecryptyException(e.getMessage());
        }
    }
    
    @Override
    public Key getKey() throws Exception {
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        final X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(this.key.getBytes()));
        return keyFactory.generatePublic(bobPubKeySpec);
    }
    
    @Override
    protected String getAligorithm() {
        return "MD5WithRSA";
    }
}
