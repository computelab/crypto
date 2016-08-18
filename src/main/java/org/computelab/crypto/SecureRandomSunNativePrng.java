package org.computelab.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public final class SecureRandomSunNativePrng implements SecureRandomFactory {

    private static final String ALGORITHM = "NativePRNG";
    private static final String PROVIDER = "SUN";
    private static final SecureRandom SEEDER;
    static {
        try {
            SEEDER = SecureRandom.getInstance(ALGORITHM, PROVIDER);
            SEEDER.setSeed(SEEDER.generateSeed(PrngConstants.SEED_SIZE));
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new AlgorithmMissingException("Missing " + PROVIDER + " " + ALGORITHM, e);
        }
    }

    @Override
    public SecureRandom newInstance() {
        try {
            SecureRandom random = SecureRandom.getInstance(ALGORITHM, PROVIDER);
            random.setSeed(SEEDER.generateSeed(PrngConstants.SEED_SIZE));
            return random;
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new AlgorithmMissingException("Missing " + PROVIDER + " " + ALGORITHM, e);
        }
    }
}
