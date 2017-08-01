package org.computelab.crypto.random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class AlphabeticRandomStringTest {

    @Test
    public void testLengthAndRange() {
        final String alphabet = "abcde";
        final RandomString randomString = new AlphabeticRandomString(
                alphabet, new SunSha1Prng().newInstance());
        for (int length = 1; length < 100; length++) {
            String str = randomString.next(length);
            assertNotNull(str);
            assertEquals(length, str.length());
            for (int i = 0; i < length; i++) {
                char c = str.charAt(i);
                assertTrue(c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e');
            }
        }
    }

    @Test
    public void testDistributionOfCharactersShouldBeNormal() {
        final int numOfStrs = 200;
        final int strLength = 64;
        final String alphabet = "abcde";
        final RandomString randomString = new AlphabeticRandomString(
                alphabet, new SunSha1Prng().newInstance());
        final Set<String> strSet = new HashSet<>();
        final Multiset<Character> charCount = HashMultiset.create();
        for (int i = 0; i < numOfStrs; i++) {
            String str = randomString.next(strLength);
            assertNotNull(str);
            assertTrue("The collision rate is near impossible: 1 / 5 ^ 64", strSet.add(str));
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                charCount.add(c);
            }
        }
        final int avgCountPerChar = strLength * numOfStrs / alphabet.length();
        final int error = avgCountPerChar / 16;
        final int maxCountPerChar = avgCountPerChar + error;
        final int minCountPerChar = avgCountPerChar - error;
        for (Character c : charCount.elementSet()) {
            assertTrue(charCount.count(c) < maxCountPerChar);
            assertTrue(charCount.count(c) > minCountPerChar);
        }
    }

    @Test
    public void testMultiThreading() throws InterruptedException, ExecutionException {
        final String alphabet = "abcdefg";
        final RandomString randomString = new AlphabeticRandomString(
                alphabet, new SunSha1Prng().newInstance());
        final int numOfStrs = 2000;
        final List<Callable<String>> tasks = new ArrayList<>(numOfStrs);
        for (int i = 0; i < numOfStrs; i++) {
            tasks.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return randomString.next(64);
                }
            });
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(200);
        final List<Future<String>> results = threadPool.invokeAll(tasks);
        final Set<String> strSet = new HashSet<>();
        boolean done = true;
        do {
            for (Future<String> future : results) {
                if (future.isDone()) {
                    strSet.add(future.get());
                }
                done = done & future.isDone();
            }
        } while (!done);
        threadPool.shutdown();
        assertEquals("Collison is impossible", numOfStrs, strSet.size());
    }

    @Test
    public void testReseeding() {
        final int length = 3;
        final SecureRandom mockRandom = mock(SecureRandom.class);
        when(mockRandom.nextInt(length)).thenReturn(0);
        final String alphabet = "a";
        final RandomString randomString = new AlphabeticRandomString(alphabet, mockRandom);
        final int numOfStrs = 50000;
        for (int i = 0; i < numOfStrs; i++) {
            randomString.next(length);
        }
        verify(mockRandom, atLeast(1)).setSeed(any());
    }

    @Test
    public void testSingleCharAlphabet() {
        final String alphabet = "a";
        final RandomString randomStr = new AlphabeticRandomString(
                alphabet, new SunSha1Prng().newInstance());
        for (int length = 1; length < 100; length++) {
            String str = randomStr.next(length);
            assertNotNull(str);
            assertEquals(length, str.length());
            for (int i = 0; i < length; i++) {
                char c = str.charAt(i);
                assertTrue(c == 'a');
            }
        }
    }

    @Test(expected=NullPointerException.class)
    public void testAlphabetCannotBeNull() {
        new AlphabeticRandomString(null, new SunSha1Prng().newInstance());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAlphabetCannotBeEmpty() {
        new AlphabeticRandomString("", new SunSha1Prng().newInstance());
    }

    @Test(expected=NullPointerException.class)
    public void testRandomCannotBeNull() {
        RandomString randomStr = new AlphabeticRandomString("a", null);
        randomStr.next(0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testStringLengthCannotBeZero() {
        RandomString randomStr = new AlphabeticRandomString("a", new SunSha1Prng().newInstance());
        randomStr.next(0);
    }
}
