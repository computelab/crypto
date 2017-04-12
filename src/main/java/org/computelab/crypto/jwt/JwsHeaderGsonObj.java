package org.computelab.crypto.jwt;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * JWS header object that can be serialized/deserialized by gson.
 */
final class JwsHeaderGsonObj implements JwsHeader {

    private String typ;
    private String cty;

    private String alg;
    private String jku;
    private String jwk;
    private String kid;
    private String x5u;
    private String x5t;
    @SerializedName("x5t#S256") private String x5tSha256;
    private List<String> x5c;
    private List<String> crit;

    @Override
    public String type() {
        return typ;
    }

    @Override
    public String contentType() {
        return cty;
    }

    @Override
    public String algorithm() {
        return alg;
    }

    @Override
    public String keySetUrl() {
        return jku;
    }

    @Override
    public String key() {
        return jwk;
    }

    @Override
    public String keyId() {
        return kid;
    }

    @Override
    public String certUrl() {
        return x5u;
    }

    @Override
    public List<String> certChain() {
        return x5c;
    }

    @Override
    public String certSha1() {
        return x5t;
    }

    @Override
    public String certSha256() {
        return x5tSha256;
    }

    @Override
    public List<String> critical() {
        return crit;
    }
}
