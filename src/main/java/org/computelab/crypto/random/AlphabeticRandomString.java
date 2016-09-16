package org.computelab.crypto.random;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.security.SecureRandom;

/**
 * Generates random strings off an alphabet. It uses {@link SecureRandom} internally
 * as the random generator.
 */
public final class AlphabeticRandomString implements RandomString {

    /** Re-seed approximately every 10000 calls. */
    private static final int SEEDING_FREQUENCY = 10000;

    private final char[] alphabet;
    private final SecureRandom random;

    public AlphabeticRandomString(final String alphabet) {
        checkNotNull(alphabet);
        checkArgument(!alphabet.isEmpty(), "The alphabet cannot be empty.");
        this.alphabet = alphabet.toCharArray();
        random = new SecureRandomSunNativePrng().newInstance();
    }

    @Override
    public String next(final int length) {
        checkArgument(length > 0, "Length must be greater than 0.");
        if (random.nextInt(SEEDING_FREQUENCY) == 0) {
            random.setSeed(random.generateSeed(PrngConstants.SEED_SIZE));
        }
        final char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = alphabet[random.nextInt(alphabet.length)];
        }
        return new String(chars);
    }
}
