package org.computelab.crypto.jwt;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class JwsHeaderImpl implements JwsHeader {

    private String alg;
    private String jku;
    private String jwk;
    private String x5u;
    private String x5t;
    @SerializedName("x5t#S256") private String x5tSha256;
    private List<String> x5c;
    private String kid;
    private String typ;
    private String cty;
    private String crit;

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
    public String certUrl() {
        return x5u;
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
    public List<String> certChain() {
        return x5c;
    }

    @Override
    public String keyId() {
        return kid;
    }

    @Override
    public String type() {
        return typ;
    }

    @Override
    public String contentType() {
        return cty;
    }

    @Override
    public String critical() {
        return crit;
    }
}
