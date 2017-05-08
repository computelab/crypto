package org.computelab.crypto.jwt;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.gson.Gson;

// TODO: Make it static with a new type Jwt, like Jwt.parseJws(string);
//       We will want a consistent interface with that of RawJws.
public class JwtParser {

    private final Gson gson = new Gson();
    private final Class<? extends JwsHeader> headerType;
    private final Class<? extends Claims> payloadType;

    public JwtParser() {
        this(JwsHeaderGsonObj.class, ClaimsGsonObj.class);
    }

    JwtParser(Class<? extends JwsHeader> headerType,
            Class<? extends Claims> payloadType) {
        checkNotNull(headerType);
        checkNotNull(payloadType);
        this.headerType = headerType;
        this.payloadType = payloadType;
    }

    public Jws parseJws(final String jwt) {
        final RawJws rawToken = RawJws.parseJwt(jwt);
        final JwsHeader header = gson.fromJson(rawToken.header(), headerType);
        final Claims payload = gson.fromJson(rawToken.payload(), payloadType);
        return new Jws() {
            @Override
            public JwsHeader header() {
                return header;
            }
            @Override
            public Claims claims() {
                return payload;
            }
            @Override
            public RawJws rawToken() {
                return rawToken;
            }
        };
    }
}
