package org.computelab.crypto.random;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SunNativePrngTest {

    @Test
    public void test() {
        assertEquals("NativePRNG", new SunNativePrng().getAlgorithm());
    }
}
