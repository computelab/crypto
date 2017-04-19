package org.computelab.crypto.jwt;

public class JwsVerifierBuilder {

    public JwsVerifierBuilder requireIssuer() {
        return this;
    }

    public JwsVerifier build() {
        return new JwsVerifier() {
            // TODO: Composite
            @Override
            public VerificationStatus verify(Jws jws) {
                // TODO Auto-generated method stub
                return null;
            }};
    }
}
