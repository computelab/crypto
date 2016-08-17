package org.computelab.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class AlphanumericRandomStringTest {

    @Test
    public void test() {
        final RandomString randomStr = new AlphanumericRandomString();
        final Set<Character> charSet = new HashSet<Character>();
        for (int i = 0; i < 100; i++) {
            final String str = randomStr.next(50);
            final char[] chars = str.toCharArray();
            for (char c : chars) {
                assertTrue("Each and every character is alphanumeric.",
                        // 0-9
                        c >= 48 && c < 58 ||
                        // A-Z
                        c >= 65 && c < 91 ||
                        // a-z
                        c >= 97 && c < 123);
                charSet.add(c);
            }
        }
        assertEquals("All the characters are covered.", 62, charSet.size());
    }

    @Test
    public void testLowerCase() {
        final RandomString randomStr = new AlphanumericRandomString(false);
        for (int i = 0; i < 100; i++) {
            final String str = randomStr.next(50);
            final char[] chars = str.toCharArray();
            for (char c : chars) {
                assertTrue(Character.isDigit(c) || Character.isLowerCase(c));
            }
        }
    }
}
