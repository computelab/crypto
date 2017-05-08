package org.computelab.crypto.jwt;

import java.security.Key;
import java.security.PublicKey;

public final class SignerFactory {

    public static Signer create(final Algorithm algorithm, final Key key) {
        switch (algorithm) {
            case HS256:
                return new HmacSigner("HmacSHA256", key);
            case HS384:
                return new HmacSigner("HmacSHA384", key);
            case HS512:
                return new HmacSigner("HmacSHA512", key);
            case RS256:
                return new RsaSigner("SHA256withRSA", (PublicKey)key);
            case RS384:
                return new RsaSigner("SHA384withRSA", (PublicKey)key);
            case RS512:
                return new RsaSigner("SHA512withRSA", (PublicKey)key);
            case ES256:
                return new RsaSigner("SHA256withECDSA", (PublicKey)key);
            case ES384:
                return new RsaSigner("SHA384withECDSA", (PublicKey)key);
            case ES512:
                return new RsaSigner("SHA512withECDSA", (PublicKey)key);
            case PS256:
                return new RsaSigner("SHA256withRSAandMGF1", (PublicKey)key);
            case PS384:
                return new RsaSigner("SHA384withRSAandMGF1", (PublicKey)key);
            case PS512:
                return new RsaSigner("SHA512withRSAandMGF1", (PublicKey)key);
            default:
                throw new UnsupportedOperationException("");
        }
    }

    private SignerFactory() {
    }
}
