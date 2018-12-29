// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.algorithm;

public class DESEncrypt extends AbstractEncrypt
{
    @Override
    protected String getAligorithm() {
        return "DES";
    }
    
    public DESEncrypt() {
    }
    
    public DESEncrypt(final Encrypt encrypt) {
        super(encrypt);
    }
    
    public DESEncrypt(final String encoding, final Encrypt encrypt) {
        super(encoding, encrypt);
    }
    
    public DESEncrypt(final String encoding) {
        super(encoding);
    }
}
