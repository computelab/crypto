package org.computelab.crypto.jwt;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.gson.Gson;

public class JwtParser {

    private final Gson gson = new Gson();
    private final Class<? extends JwtHeader> headerType;
    private final Class<? extends JwtClaims> payloadType;

    public JwtParser(Class<? extends JwtHeader> headerType,
            Class<? extends JwtClaims> payloadType) {
        checkNotNull(headerType);
        checkNotNull(payloadType);
        this.headerType = headerType;
        this.payloadType = payloadType;
    }

    public Jwt parse(final String jwt) {
        final RawToken rawToken = RawToken.parse(jwt);
        final JwtHeader header = gson.fromJson(rawToken.header(), headerType);
        final JwtClaims payload = gson.fromJson(rawToken.payload(), payloadType);
        return new Jwt() {
            @Override
            public JwtHeader header() {
                return header;
            }
            @Override
            public JwtClaims claims() {
                return payload;
            }
            @Override
            public String signature() {
                return rawToken.signature();
            }
        };
    }
}
