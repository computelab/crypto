package org.computelab.crypto.random;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SunNativePrngBlockingTest {

    @Test
    public void test() {
        assertEquals("NativePRNGBlocking", new SunNativePrngBlocking().getAlgorithm());
    }
}
