package org.computelab.crypto.jwt;

import java.util.List;

/**
 * Registered headers for JWS (RFC 7515 JSON Web Signature).
 */
public interface JwsHeader {

    /**
     * The media type. Default is "JWT".
     */
    String type();

    /**
     * Used for nested signing or encryption, in which case this header
     * MUST be present and the value MUST be "JWT", to indicate that a
     * nested JWT is carried in this JWT.
     */
    String contentType();

    /**
     * The algorithm used to secure the token. Signed or MACed.
     */
    String algorithm();

    /**
     * Refers to a resource for a set of JSON-encoded public keys,
     * one of which corresponds to the key used to digitally sign
     * the JWS.
     */
    String keySetUrl();

    /**
     * The public key that corresponds to the key used to
     * digitally sign the JWS.
     */
    String key();

    /**
     * ID of the public key used to digitally sign the JWS.
     */
    String keyId();

    /**
     * A resource for the X.509 public key certificate or certificate
     * chain corresponding to the key used to digitally sign the JWS.
     */
    String certUrl();

    /**
     * The X.509 public key certificate or certificate chain
     * corresponding to the key used to digitally sign the JWS.
     */
    List<String> certChain();

    /**
     * Base64 URL-encoded SHA-1 thumb-print (a.k.a. digest) of
     * the DER encoding of the X.509 certificate.
     */
    String certSha1();

    /**
     * Base64 URL-encoded SHA-256 thumb-print (a.k.a. digest) of
     * the DER encoding of the X.509 certificate.
     */
    String certSha256();

    /**
     * Indicates that extensions MUST be understood and processed.
     * If any of the listed extension headers are not understood
     * and supported by the recipient, then the JWS is invalid.
     */
    List<String> critical();
}
