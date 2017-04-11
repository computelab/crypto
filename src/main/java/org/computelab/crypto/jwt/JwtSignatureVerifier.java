package org.computelab.crypto.jwt;

public class JwtSignatureVerifier implements JwtVerifier {

    @Override
    public VerificationResult verify(Jwt jwt) {
        jwt.header().algorithm();
        jwt.signature();
        return new VerificationResult();
    }
}
