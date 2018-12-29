// 
// Decompiled by Procyon v0.5.29
// 

package com.github.bomberjin.utils.cipher.util;

public class Base64
{
    static final int CHUNK_SIZE = 76;
    static final byte[] CHUNK_SEPARATOR;
    static final int BASELENGTH = 255;
    static final int LOOKUPLENGTH = 64;
    static final int EIGHTBIT = 8;
    static final int SIXTEENBIT = 16;
    static final int TWENTYFOURBITGROUP = 24;
    static final int FOURBYTE = 4;
    static final int SIGN = -128;
    static final byte PAD = 61;
    private static byte[] base64Alphabet;
    private static byte[] lookUpBase64Alphabet;
    
    private static boolean isBase64(final byte octect) {
        return octect == 61 || Base64.base64Alphabet[octect] != -1;
    }
    
    public static boolean isArrayByteBase64(byte[] arrayOctect) {
        arrayOctect = discardWhitespace(arrayOctect);
        final int length = arrayOctect.length;
        if (length == 0) {
            return true;
        }
        for (int i = 0; i < length; ++i) {
            if (!isBase64(arrayOctect[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static byte[] encodeBase64(final byte[] binaryData) {
        return encodeBase64(binaryData, false);
    }
    
    public static byte[] encodeBase64Chunked(final byte[] binaryData) {
        return encodeBase64(binaryData, true);
    }
    
    public Object decode(final Object pObject) {
        if (!(pObject instanceof byte[])) {
            throw new RuntimeException("Parameter supplied to Base64 decode is not a byte[]");
        }
        return this.decode((byte[])pObject);
    }
    
    public byte[] decode(final byte[] pArray) {
        return decodeBase64(pArray);
    }
    
    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked) {
        final int lengthDataBits = binaryData.length * 8;
        final int fewerThan24bits = lengthDataBits % 24;
        final int numberTriplets = lengthDataBits / 24;
        byte[] encodedData = null;
        int encodedDataLength = 0;
        int nbrChunks = 0;
        if (fewerThan24bits != 0) {
            encodedDataLength = (numberTriplets + 1) * 4;
        }
        else {
            encodedDataLength = numberTriplets * 4;
        }
        if (isChunked) {
            nbrChunks = ((Base64.CHUNK_SEPARATOR.length != 0) ? ((int)Math.ceil(encodedDataLength / 76.0f)) : 0);
            encodedDataLength += nbrChunks * Base64.CHUNK_SEPARATOR.length;
        }
        encodedData = new byte[encodedDataLength];
        byte k = 0;
        byte l = 0;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        int encodedIndex = 0;
        int dataIndex = 0;
        int i = 0;
        int nextSeparatorIndex = 76;
        int chunksSoFar = 0;
        for (i = 0; i < numberTriplets; ++i) {
            dataIndex = i * 3;
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            b3 = binaryData[dataIndex + 2];
            l = (byte)(b2 & 0xF);
            k = (byte)(b1 & 0x3);
            final byte val1 = ((b1 & 0xFFFFFF80) != 0x0) ? ((byte)(b1 >> 2 ^ 0xC0)) : ((byte)(b1 >> 2));
            final byte val2 = ((b2 & 0xFFFFFF80) != 0x0) ? ((byte)(b2 >> 4 ^ 0xF0)) : ((byte)(b2 >> 4));
            final byte val3 = ((b3 & 0xFFFFFF80) != 0x0) ? ((byte)(b3 >> 6 ^ 0xFC)) : ((byte)(b3 >> 6));
            encodedData[encodedIndex] = Base64.lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = Base64.lookUpBase64Alphabet[val2 | k << 4];
            encodedData[encodedIndex + 2] = Base64.lookUpBase64Alphabet[l << 2 | val3];
            encodedData[encodedIndex + 3] = Base64.lookUpBase64Alphabet[b3 & 0x3F];
            encodedIndex += 4;
            if (isChunked && encodedIndex == nextSeparatorIndex) {
                System.arraycopy(Base64.CHUNK_SEPARATOR, 0, encodedData, encodedIndex, Base64.CHUNK_SEPARATOR.length);
                ++chunksSoFar;
                nextSeparatorIndex = 76 * (chunksSoFar + 1) + chunksSoFar * Base64.CHUNK_SEPARATOR.length;
                encodedIndex += Base64.CHUNK_SEPARATOR.length;
            }
        }
        dataIndex = i * 3;
        if (fewerThan24bits == 8) {
            b1 = binaryData[dataIndex];
            k = (byte)(b1 & 0x3);
            final byte val1 = ((b1 & 0xFFFFFF80) != 0x0) ? ((byte)(b1 >> 2 ^ 0xC0)) : ((byte)(b1 >> 2));
            encodedData[encodedIndex] = Base64.lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = Base64.lookUpBase64Alphabet[k << 4];
            encodedData[encodedIndex + 3] = (encodedData[encodedIndex + 2] = 61);
        }
        else if (fewerThan24bits == 16) {
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            l = (byte)(b2 & 0xF);
            k = (byte)(b1 & 0x3);
            final byte val1 = ((b1 & 0xFFFFFF80) != 0x0) ? ((byte)(b1 >> 2 ^ 0xC0)) : ((byte)(b1 >> 2));
            final byte val2 = ((b2 & 0xFFFFFF80) != 0x0) ? ((byte)(b2 >> 4 ^ 0xF0)) : ((byte)(b2 >> 4));
            encodedData[encodedIndex] = Base64.lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = Base64.lookUpBase64Alphabet[val2 | k << 4];
            encodedData[encodedIndex + 2] = Base64.lookUpBase64Alphabet[l << 2];
            encodedData[encodedIndex + 3] = 61;
        }
        if (isChunked && chunksSoFar < nbrChunks) {
            System.arraycopy(Base64.CHUNK_SEPARATOR, 0, encodedData, encodedDataLength - Base64.CHUNK_SEPARATOR.length, Base64.CHUNK_SEPARATOR.length);
        }
        return encodedData;
    }
    
    public static byte[] decodeBase64(byte[] base64Data) {
        base64Data = discardNonBase64(base64Data);
        if (base64Data.length == 0) {
            return new byte[0];
        }
        final int numberQuadruple = base64Data.length / 4;
        byte[] decodedData = null;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        byte marker0 = 0;
        byte marker = 0;
        int encodedIndex = 0;
        int dataIndex = 0;
        int lastData = base64Data.length;
        while (base64Data[lastData - 1] == 61) {
            if (--lastData == 0) {
                return new byte[0];
            }
        }
        decodedData = new byte[lastData - numberQuadruple];
        for (int i = 0; i < numberQuadruple; ++i) {
            dataIndex = i * 4;
            marker0 = base64Data[dataIndex + 2];
            marker = base64Data[dataIndex + 3];
            b1 = Base64.base64Alphabet[base64Data[dataIndex]];
            b2 = Base64.base64Alphabet[base64Data[dataIndex + 1]];
            if (marker0 != 61 && marker != 61) {
                b3 = Base64.base64Alphabet[marker0];
                b4 = Base64.base64Alphabet[marker];
                decodedData[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte)((b2 & 0xF) << 4 | (b3 >> 2 & 0xF));
                decodedData[encodedIndex + 2] = (byte)(b3 << 6 | b4);
            }
            else if (marker0 == 61) {
                decodedData[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
            }
            else if (marker == 61) {
                b3 = Base64.base64Alphabet[marker0];
                decodedData[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte)((b2 & 0xF) << 4 | (b3 >> 2 & 0xF));
            }
            encodedIndex += 3;
        }
        return decodedData;
    }
    
    static byte[] discardWhitespace(final byte[] data) {
        final byte[] groomedData = new byte[data.length];
        int bytesCopied = 0;
        int i = 0;
        while (i < data.length) {
            switch (data[i]) {
                default: {
                    groomedData[bytesCopied++] = data[i];
                }
                case 9:
                case 10:
                case 13:
                case 32: {
                    ++i;
                    continue;
                }
            }
        }
        final byte[] packedData = new byte[bytesCopied];
        System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
        return packedData;
    }
    
    static byte[] discardNonBase64(final byte[] data) {
        final byte[] groomedData = new byte[data.length];
        int bytesCopied = 0;
        for (int i = 0; i < data.length; ++i) {
            if (isBase64(data[i])) {
                groomedData[bytesCopied++] = data[i];
            }
        }
        final byte[] packedData = new byte[bytesCopied];
        System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
        return packedData;
    }
    
    public Object encode(final Object pObject) {
        if (!(pObject instanceof byte[])) {
            throw new RuntimeException("Parameter supplied to Base64 encode is not a byte[]");
        }
        return this.encode((byte[])pObject);
    }
    
    public byte[] encode(final byte[] pArray) {
        return encodeBase64(pArray, false);
    }
    
    static {
        CHUNK_SEPARATOR = "\r\n".getBytes();
        Base64.base64Alphabet = new byte[255];
        Base64.lookUpBase64Alphabet = new byte[64];
        for (int i = 0; i < 255; ++i) {
            Base64.base64Alphabet[i] = -1;
        }
        for (int i = 90; i >= 65; --i) {
            Base64.base64Alphabet[i] = (byte)(i - 65);
        }
        for (int i = 122; i >= 97; --i) {
            Base64.base64Alphabet[i] = (byte)(i - 97 + 26);
        }
        for (int i = 57; i >= 48; --i) {
            Base64.base64Alphabet[i] = (byte)(i - 48 + 52);
        }
        Base64.base64Alphabet[43] = 62;
        Base64.base64Alphabet[47] = 63;
        for (int i = 0; i <= 25; ++i) {
            Base64.lookUpBase64Alphabet[i] = (byte)(65 + i);
        }
        for (int i = 26, j = 0; i <= 51; ++i, ++j) {
            Base64.lookUpBase64Alphabet[i] = (byte)(97 + j);
        }
        for (int i = 52, j = 0; i <= 61; ++i, ++j) {
            Base64.lookUpBase64Alphabet[i] = (byte)(48 + j);
        }
        Base64.lookUpBase64Alphabet[62] = 43;
        Base64.lookUpBase64Alphabet[63] = 47;
    }
}
