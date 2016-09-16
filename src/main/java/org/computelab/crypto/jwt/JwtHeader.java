package org.computelab.crypto.jwt;

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
