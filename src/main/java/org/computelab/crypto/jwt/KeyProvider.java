package org.computelab.crypto.jwt;

import java.security.Key;

public interface KeyProvider {

    Key getKey(JwsHeader header);
}
