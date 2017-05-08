package org.computelab.crypto.jwt;

/**
 * Provides a unified interface for HMAC and RSA.
 */
public interface Signer {

    boolean verify(RawJws rawJws);
}
