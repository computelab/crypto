package org.computelab.crypto.jwt;

/**
 * These are the registered JWT claims. See RFC 7519 JSON Web Token (JWT)
 * for the specifications.
 */
public interface Claims {

    /**
     * The issuer.
     */
    String issuer();

    /**
     * The subject.
     */
    String subject();

    /**
     * The audience. The intended recipient.
     */
    String audience();

    /**
     * The epoch time after which the token expires.
     */
    Long expiration();

    /**
     * The epoch time before which the token must not be accepted.
     */
    Long notBefore();

    /**
     * The epoch time at which the token was issued.
     */
    Long issuedAt();

    /**
     * Token ID. Unique identifier for the token.
     */
    String jwtId();
}
