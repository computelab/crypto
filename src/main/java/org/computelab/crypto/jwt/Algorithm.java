package org.computelab.crypto.jwt;

/**
 * See RFC 7518 JSON Web Algorithms (JWA).
 */
public enum Algorithm {

    /**
     * HMAC using SHA-256. Required.
     */
    HS256,

    /**
     * HMAC using SHA-384.
     */
    HS384,

    /**
     * HMAC using SHA-512.
     */
    HS512,

    /**
     * RSASSA-PKCS1-v1_5 using SHA-256. Recommended.
     */
    RS256,

    /**
     * RSASSA-PKCS1-v1_5 using SHA-384.
     */
    RS384,

    /**
     * RSASSA-PKCS1-v1_5 using SHA-512.
     */
    RS512,

    /**
     * ECDSA using P-256 and SHA-256. Recommended.
     */
    ES256,

    /**
     * ECDSA using P-384 and SHA-384.
     */
    ES384,

    /**
     * ECDSA using P-512 and SHA-512.
     */
    ES512,

    /**
     * RSASSA-PSS using SHA-256 and MGF1 with SHA-256.
     */
    PS256,

    /**
     * RSASSA-PSS using SHA-384 and MGF1 with SHA-384.
     */
    PS384,

    /**
     * RSASSA-PSS using SHA-512 and MGF1 with SHA-512.
     */
    PS512,

    /**
     * No digital signature or MAC performed.
     */
    None
}
