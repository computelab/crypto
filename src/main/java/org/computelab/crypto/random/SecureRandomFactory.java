package org.computelab.crypto.random;

import java.security.SecureRandom;

public interface SecureRandomFactory {

    SecureRandom newInstance();
}
