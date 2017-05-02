package org.computelab.crypto.random;

import static com.google.common.base.Preconditions.checkNotNull;

import java.security.SecureRandom;

/**
 * Random strings composed of printable ASCII characters except for space and delete.
 */
public final class PrintableCharRandomString implements RandomString {

    private final RandomString randomString;

    public PrintableCharRandomString() {
        this(new SunSha1Prng().newInstance());
    }

    public PrintableCharRandomString(final SecureRandom random) {
        checkNotNull(random);
        final char begin = '!';
        final char end = '~';
        final char[] chars = new char[end - begin + 1];
        for (char c = begin; c <= end; c++) {
            chars[c - begin] = c;
        }
        randomString = new AlphabeticRandomString(new String(chars), random);
    }

    @Override
    public String next(int length) {
        return randomString.next(length);
    }
}
