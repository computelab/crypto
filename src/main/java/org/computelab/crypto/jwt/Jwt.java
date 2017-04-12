package org.computelab.crypto.jwt;

/**
 * JSON web token of signed claims. Note this is a subset of
 * RFC 7519 JWT not including JWE.
 */
public interface Jwt {

    /**
     * Token header.
     */
    JwsHeader header();

    /**
     * The claims.
     */
    Claims claims();

    /**
     * Base64 URL encoded signature string.
     */
    String signature();
}
