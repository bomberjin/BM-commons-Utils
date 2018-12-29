// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.exception;

public class EncryptyException extends RuntimeException
{
    private static final long serialVersionUID = -1350294746879051289L;
    
    public EncryptyException() {
    }
    
    public EncryptyException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public EncryptyException(final String message) {
        super(message);
    }
    
    public EncryptyException(final Throwable cause) {
        super(cause);
    }
}
