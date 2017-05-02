package org.computelab.crypto.random;

/**
 * This usually connects to /dev/urandom on UNIX-like systems.
 */
public final class SunNativePrngNonBlocking extends AbstractSunNative {

    @Override
    String getAlgorithm() {
        return "NativePRNGNonBlocking";
    }
}
