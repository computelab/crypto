package org.computelab.crypto.random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PrintableCharacterRandomStringTest {

    @Test
    public void test() {
        final RandomString randomStr = new PrintableCharacterRandomString();
        final Set<Character> charSet = new HashSet<Character>();
        for (int i = 0; i < 100; i++) {
            final String str = randomStr.next(50);
            final char[] chars = str.toCharArray();
            for (char c : chars) {
                assertTrue(c >= 33 && c < 127);
                charSet.add(c);
            }
        }
        assertEquals("All the characters are covered.", 94, charSet.size());
    }
}
