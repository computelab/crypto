package org.computelab.crypto.jwt;

import java.security.Key;

public class JwsSignatureVerifier implements JwsVerifier {

    private final KeyProvider keyProvider;

    public JwsSignatureVerifier(final KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    @Override
    public VerificationStatus verify(final Jws jws) {
        final Key key = keyProvider.getKey(jws.header());
        final Algorithm algorithm = Algorithm.valueOf(jws.header().algorithm());
        final Signer signer = SignerFactory.create(algorithm, key);
        final boolean valid = signer.verify(jws.rawToken());
        if (valid) {
            return VerificationStatus.VALID;
        }
        return VerificationStatus.INVALID_SIGNATURE;
    }
}
