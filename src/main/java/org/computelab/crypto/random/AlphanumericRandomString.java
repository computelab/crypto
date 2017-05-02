package org.computelab.crypto.random;

import static com.google.common.base.Preconditions.checkNotNull;

import java.security.SecureRandom;

/**
 * Random strings composed of letters a-zA-Z and digits 0-9.
 */
public final class AlphanumericRandomString implements RandomString {

    private final RandomString randomString;

    /**
     * Random string of lower case letters a-z and digits 0-9.
     * Random service uses Sun Sha1 PRNG.
     */
    public AlphanumericRandomString() {
        this(true);
    }

    /**
     * Random string of letters a-z (and A-Z if case sensitive is true) and digits 0-9.
     * Random service uses Sun Sha1 PRNG.
     *
     * @param caseSensitive
     *            Whether the random string is case-sensitive. If true, the random string is
     *            composed of upper cases A-Z, lower cases a-z, and digits 0-9 of equal
     *            probability. If false, only lower case letters and digits are used.
     */
    public AlphanumericRandomString(final boolean caseSensitive) {
        this(caseSensitive, new SunSha1Prng().newInstance());
    }

    /**
     * Random string of letters a-z (and A-Z if case sensitive is true) and digits 0-9.
     * Uses the specified secure random service.
     *
     * @param caseSensitive
     *            Whether the random string is case-sensitive. If true, the random string is
     *            composed of upper cases A-Z, lower cases a-z, and digits 0-9 of equal
     *            probability. If false, only lower case letters and digits are used.
     * @param random
     *            The underlying secure random service
     */
    public AlphanumericRandomString(final boolean caseSensitive, final SecureRandom random) {

        checkNotNull(random);

        final int sizeLowerCase = '9' - '0' + 1 + 'z' - 'a' + 1;
        final int sizeAll = sizeLowerCase + 'Z' - 'A' + 1;
        final char[] chars = caseSensitive ? new char[sizeAll] : new char[sizeLowerCase];

        int i = 0;
        // 0-9
        for (char c = '0'; c <= '9'; c++) {
            chars[i] = c;
            i++;
        }
        // a-z
        for (char c = 'a'; c <= 'z'; c++) {
            chars[i] = c;
            i++;
        }
        // A-Z
        if (caseSensitive) {
            for (char c = 'A'; c <= 'Z'; c++) {
                chars[i] = c;
                i++;
            }
        }
        randomString = new AlphabeticRandomString(new String(chars), random);
    }

    @Override
    public String next(int length) {
        return randomString.next(length);
    }
}
