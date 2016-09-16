package org.computelab.crypto.jwt;

public interface Jwt {

    /**
     * Token header.
     */
    JwtHeader header();

    /**
     * The payload. The claims.
     */
    JwtPayload payload();

    /**
     * Base64-encoded signature string.
     */
    String signature();
}
