package org.computelab.crypto.random;

/**
 * No assertions are made as to the blocking nature of generating these numbers.
 */
public final class SunNativePrng extends AbstractSunNative {

    @Override
    String getAlgorithm() {
        return "NativePRNG";
    }
}
