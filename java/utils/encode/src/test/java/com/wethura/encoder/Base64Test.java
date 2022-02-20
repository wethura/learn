package com.wethura.encoder;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author sola
 */
public class Base64Test extends Assert {

    public static final Encoder encoder = Base64.getEncoder();
    public static final Decoder decoder = Base64.getDecoder();

    @Test
    public void testBase64() {
        final String str = "testEncodeAndDecodeString";

        final String encodedStr = new String(encoder.encode(str.getBytes()));
        final String decodedStr = new String(decoder.decode(encodedStr.getBytes()));

        assertEquals("the encode and decode string is not equal to the origin str.w", decodedStr, str);
    }

}
