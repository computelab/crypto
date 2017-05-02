package org.computelab.crypto.random;

import java.security.SecureRandom;

/**
 * Creates SecureRandom services using explicit providers and algorithms.
 *
 * Note, among the providers, the "SUN" provider is a good one. Avoid the retired
 * Apache Harmony named "crypto".
 */
public interface SecureRandomFactory {

    SecureRandom newInstance();
}
