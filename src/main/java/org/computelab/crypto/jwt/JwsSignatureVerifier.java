package org.computelab.crypto.jwt;

public class JwsSignatureVerifier implements JwsVerifier {

    @Override
    public VerificationStatus verify(final Jws jws) {
        // TODO: Verify signature
        final String algorithm = jws.header().algorithm();
        final String signature = jws.rawToken().signature();
        if (algorithm != null && signature != null) {
            return VerificationStatus.VALID;
        }
        return VerificationStatus.INVALID_SIGNATURE;
    }
}
