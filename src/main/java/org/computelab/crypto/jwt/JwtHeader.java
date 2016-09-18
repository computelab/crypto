package org.computelab.crypto.jwt;

/**
 * The most common headers. Extend this interface if more headers are needed.
 *
 * For specifications of all the registered headers, please see:
 *
 * RFC 7519 JSON Web Token (JWT)
 * RFC 7515 JSON Web Signature (JWS)
 * RFC 7516 JSON Web Encryption (JWE)
 *
 */
public interface JwtHeader {

    /**
     * The media type. Default is JWT.
     */
    String type();

    /**
     * The algorithm used to sign the token.
     */
    String algorithm();
}
