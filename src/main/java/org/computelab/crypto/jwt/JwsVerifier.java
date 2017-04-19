package org.computelab.crypto.jwt;

public interface JwsVerifier {

    VerificationStatus verify(Jws jws);
}
