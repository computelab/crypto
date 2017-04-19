package org.computelab.crypto.jwt;

public enum VerificationStatus {
    VALID,
    INVALID_SIGNATURE,
    INVALID_ISSUER,
    EXPIRED
}
