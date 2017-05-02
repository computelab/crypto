package org.computelab.crypto.random;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.computelab.crypto.AlgorithmMissingException;

/**
 * If you care more about performance, use this one. It computes
 * the SHA-1 hash over a true-random seed value concatenated with
 * a 64-bit counter which is incremented by 1 for each operation.
 */
public final class SunSha1Prng implements SecureRandomFactory {

    private static final String ALGORITHM = "SHA1PRNG";
    private static final String PROVIDER = "SUN";

    @Override
    public SecureRandom newInstance() {
        try {
            final SecureRandom seeder = new SunNativePrng().newInstance();
            final SecureRandom random = SecureRandom.getInstance(ALGORITHM, PROVIDER);
            random.setSeed(seeder.generateSeed(PrngConstants.SEED_SIZE));
            return random;
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new AlgorithmMissingException("Missing " + PROVIDER + " " + ALGORITHM, e);
        }
    }
}
