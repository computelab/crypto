package org.computelab.crypto.random;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.security.SecureRandom;

/**
 * Generates random strings off an alphabet.
 */
public final class AlphabeticRandomString implements RandomString {

    /** Reseed approximately every 10000 calls. */
    private static final int RESEED_FREQUENCY = 10000;
    
    /** Reseed using Sun Sha1 PRNG for speed. */
    private final SecureRandom reseed = new SunSha1Prng().newInstance();

    private final char[] alphabet;
    private final SecureRandom random;

    public AlphabeticRandomString(final String alphabet, final SecureRandom random) {
        checkNotNull(alphabet);
        checkArgument(!alphabet.isEmpty(), "The alphabet cannot be empty.");
        checkNotNull(random);
        this.alphabet = alphabet.toCharArray();
        this.random = random;
    }

    @Override
    public String next(final int length) {
        checkArgument(length > 0, "Length must be greater than 0.");
        if (reseed.nextInt(RESEED_FREQUENCY) == 0) {
            random.setSeed(reseed.generateSeed(PrngConstants.SEED_SIZE));
        }
        final char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = alphabet[random.nextInt(alphabet.length)];
        }
        return new String(chars);
    }
}
