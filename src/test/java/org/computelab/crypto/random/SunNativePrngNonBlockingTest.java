package org.computelab.crypto.random;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SunNativePrngNonBlockingTest {

    @Test
    public void test() {
        assertEquals("NativePRNGNonBlocking", new SunNativePrngNonBlocking().getAlgorithm());
    }
}
