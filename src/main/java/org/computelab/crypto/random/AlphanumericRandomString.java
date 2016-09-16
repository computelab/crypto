package org.computelab.crypto.random;

/**
 * Random strings composed of letters a-zA-Z and digits 0-9.
 */
public final class AlphanumericRandomString implements RandomString {

    private final RandomString randomString;

    public AlphanumericRandomString() {
        this(true);
    }

    /**
     * @param caseSensitive
     *            Whether the random string is case-sensitive. If true, the random string is
     *            composed of upper cases A-Z, lower cases a-z, and digits 0-9 of equal
     *            probability. If false, only lower case letters and digits are used.
     */
    public AlphanumericRandomString(final boolean caseSensitive) {

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
        randomString = new AlphabeticRandomString(new String(chars));
    }

    @Override
    public String next(int length) {
        return randomString.next(length);
    }
}
