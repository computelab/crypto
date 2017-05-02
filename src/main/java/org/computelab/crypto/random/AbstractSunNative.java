package org.computelab.crypto.random;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.computelab.crypto.AlgorithmMissingException;

/**
 * Has a cached seeder that is based on native PRNG.
 */
abstract class AbstractSunNative implements SecureRandomFactory {

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
        final String algo = getAlgorithm();
        try {
            final SecureRandom random = SecureRandom.getInstance(algo, PROVIDER);
            random.setSeed(SEEDER.generateSeed(PrngConstants.SEED_SIZE));
            return random;
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new AlgorithmMissingException("Missing " + PROVIDER + " " + algo, e);
        }
    }

    abstract String getAlgorithm();
}
