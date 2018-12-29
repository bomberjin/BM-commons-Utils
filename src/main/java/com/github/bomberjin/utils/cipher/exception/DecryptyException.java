// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.exception;

public class DecryptyException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public DecryptyException(final Exception e) {
    }
    
    public DecryptyException() {
    }
    
    public DecryptyException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public DecryptyException(final String message) {
        super(message);
    }
    
    public DecryptyException(final Throwable cause) {
        super(cause);
    }
}
