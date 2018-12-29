// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.algorithm;

public class DESDecrypt extends AbstractDecrypt
{
    @Override
    protected String getAligorithm() {
        return "DES";
    }
    
    public DESDecrypt(final String encoding, final String key) {
        super(encoding, key);
    }
    
    public DESDecrypt(final Decrypt decrypt) {
        super("", decrypt);
    }
    
    public DESDecrypt() {
        super("");
    }
    
    public DESDecrypt(final String key) {
        super(key);
    }
    
    public DESDecrypt(final String key, final Decrypt decrypt) {
        super(key, decrypt);
    }
    
    public DESDecrypt(final String key, final String encoding, final Decrypt decrypt) {
        super(key, encoding, decrypt);
    }
    
//    public static void main(final String[] args) {
//        final String dataContent = "\u5b89\u9759\u4e86\u623f\u95f4\u7231\u4e0a\u4e86\u98de\u673a\u6765\u8bf4\u7b80\u5355sdsdf";
//        final DESEncrypt desEncrypt = new DESEncrypt();
//        final String desEncryptStr = desEncrypt.encrypty(dataContent);
//        System.out.println(desEncryptStr);
//        final DESDecrypt desDecrypt = new DESDecrypt();
//        System.out.println(desDecrypt.decrypty(desEncryptStr));
//    }
}
