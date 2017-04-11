package org.computelab.crypto.jwt;

public interface JwtVerifier {

    VerificationResult verify(Jwt jwt);
}
