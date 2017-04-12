package org.computelab.crypto.jwt;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.gson.Gson;

public class JwtParser {

    private final Gson gson = new Gson();
    private final Class<? extends JwsHeader> headerType;
    private final Class<? extends Claims> payloadType;

    public JwtParser(Class<? extends JwsHeader> headerType,
            Class<? extends Claims> payloadType) {
        checkNotNull(headerType);
        checkNotNull(payloadType);
        this.headerType = headerType;
        this.payloadType = payloadType;
    }

    public Jwt parse(final String jwt) {
        final RawToken rawToken = RawToken.parse(jwt);
        final JwsHeader header = gson.fromJson(rawToken.header(), headerType);
        final Claims payload = gson.fromJson(rawToken.payload(), payloadType);
        return new Jwt() {
            @Override
            public JwsHeader header() {
                return header;
            }
            @Override
            public Claims claims() {
                return payload;
            }
            @Override
            public String signature() {
                return rawToken.signature();
            }
        };
    }
}
