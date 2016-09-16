package org.computelab.crypto.random;

import static org.junit.Assert.assertFalse;

import java.security.SecureRandom;

import org.junit.Test;

public class SecureRandomSunSha1PrngTest {

    @Test
    public void test() {
        SecureRandom random = new SecureRandomSunSha1Prng().newInstance();
        assertFalse(random.nextLong() == random.nextLong());
    }
}
