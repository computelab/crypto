package org.computelab.crypto;

/**
 * A string of random characters. Use random strings in place of sequential numbers as IDs. Random
 * strings have the following advantages over sequential numbers:
 * <ol>
 * <li>Do not reveal business data, especially if the IDs are externally exposed.</li>
 * <li>Do not invite additional logic, such as ordering, on top of IDs.</li>
 * <li>Avoid the maintenance of ordering (e.g. no more gap locking).</li>
 * <li>Facilitate even distribution when hashing the keys.</li>
 * <li>Strings have infinite capacities.</li>
 * <li>Strings are a more generic form for carrying data.</li>
 * <ol>
 * Besides IDs, random strings can also be used for encryption, de-identification, and compression.
 */
public interface RandomString {

    /**
     * Generates a string of randomly selected characters.
     *
     * @param length The length of the string to be generated
     * @return A newly generated string of randomly selected characters
     */
    String next(int length);
}
