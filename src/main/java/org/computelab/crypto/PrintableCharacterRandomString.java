package org.computelab.crypto;

/**
 * Random strings composed of printable ASCII characters except for space and delete.
 */
public final class PrintableCharacterRandomString implements RandomString {

    private final RandomString randomString;

    public PrintableCharacterRandomString() {
        final char begin = '!';
        final char end = '~';
        final char[] chars = new char[end - begin + 1];
        for (char c = begin; c <= end; c++) {
            chars[c - begin] = c;
        }
        randomString = new AlphabeticRandomString(new String(chars));
    }

    @Override
    public String next(int length) {
        return randomString.next(length);
    }
}
