package org.computelab.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public final class SecureRandomSunSha1Prng implements SecureRandomFactory {

    private static final String ALGORITHM = "SHA1PRNG";
    private static final String PROVIDER = "SUN";

    @Override
    public SecureRandom newInstance() {
        try {
            SecureRandom seeder = new SecureRandomSunNativePrng().newInstance();
            SecureRandom random = SecureRandom.getInstance(ALGORITHM, PROVIDER);
            random.setSeed(seeder.generateSeed(PrngConstants.SEED_SIZE));
            return random;
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new AlgorithmMissingException("Missing " + PROVIDER + " " + ALGORITHM, e);
        }
    }
}
