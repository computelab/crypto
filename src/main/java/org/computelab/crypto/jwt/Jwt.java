package org.computelab.crypto.jwt;

public interface Jwt {

    /**
     * Token header.
     */
    JwtHeader header();

    /**
     * The claims.
     */
    JwtClaims claims();

    /**
     * Base64 encoded signature string.
     */
    String signature();
}
