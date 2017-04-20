package org.computelab.crypto.jwt;

/**
 * RFC 7515 JSON Web Signature (JWS). JSON web token of signed claims.
 * Note this is the JWS subset of RFC 7519 JWT not including JWE. On
 * the other hand a JWT is ALWAYS the compact form of either a JWS
 * or a JWE.
 */
public interface Jws {

    /**
     * The header.
     */
    JwsHeader header();

    /**
     * The claims.
     */
    Claims claims();

    /**
     * Unpacked token in raw JSON.
     */
    RawJws rawToken();
}
