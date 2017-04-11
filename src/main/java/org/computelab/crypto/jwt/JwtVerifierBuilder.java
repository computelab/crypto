package org.computelab.crypto.jwt;

public class JwtVerifierBuilder {

    public JwtVerifierBuilder requireIssuer() {
        return this;
    }

    public JwtVerifier build() {
        return new JwtVerifier() {
            // TODO: Composite
            @Override
            public VerificationResult verify(Jwt jwt) {
                // TODO Auto-generated method stub
                return null;
            }};
    }
}
