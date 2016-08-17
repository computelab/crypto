package org.computelab.crypto;

import java.security.SecureRandom;

public interface SecureRandomFactory {

    SecureRandom newInstance();
}
