package org.computelab.crypto.jwt;

public class BasicJwtHeader implements JwtHeader {

    private String typ;
    private String alg;

    @Override
    public String type() {
        return typ;
    }

    @Override
    public String algorithm() {
        return alg;
    }
}
