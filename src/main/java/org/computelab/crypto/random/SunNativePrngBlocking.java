package org.computelab.crypto.random;

/**
 * This usually connects to /dev/random on UNIX-like systems.
 */
public final class SunNativePrngBlocking extends AbstractSunNative {

    @Override
    String getAlgorithm() {
        return "NativePRNGBlocking";
    }
}
