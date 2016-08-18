package org.computelab.crypto;

import static org.junit.Assert.assertFalse;

import java.security.SecureRandom;

import org.junit.Test;

public class SecureRandomFactoryTest {

    @Test
    public void test() {
        SecureRandomFactory sunNativePrng = new SecureRandomSunNativePrng();
        SecureRandom sr1 = sunNativePrng.newInstance();
        SecureRandom sr2 = sunNativePrng.newInstance();
        assertFalse(sr1 == sr2);
        assertFalse(sr1.nextLong() == sr2.nextLong());
    }
}
