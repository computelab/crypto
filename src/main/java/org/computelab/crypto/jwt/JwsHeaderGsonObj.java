package org.computelab.crypto.jwt;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * JWS header object that can be serialized/deserialized by gson.
 */
final class JwsHeaderGsonObj implements JwsHeader {

    @SerializedName("typ")
    private String type;

    @SerializedName("cty")
    private String contentType;

    @SerializedName("alg")
    private String algorithm;

    @SerializedName("jku")
    private String keySetUrl;

    @SerializedName("jwk")
    private String key;

    @SerializedName("kid")
    private String keyId;

    @SerializedName("x5u")
    private String certUrl;

    @SerializedName("x5t")
    private String certSha1;

    @SerializedName("x5t#S256")
    private String certSha256;

    @SerializedName("x5c")
    private List<String> certChain;

    @SerializedName("crit")
    private List<String> critical;

    @Override
    public String type() {
        return type;
    }

    @Override
    public String contentType() {
        return contentType;
    }

    @Override
    public String algorithm() {
        return algorithm;
    }

    @Override
    public String keySetUrl() {
        return keySetUrl;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String keyId() {
        return keyId;
    }

    @Override
    public String certUrl() {
        return certUrl;
    }

    @Override
    public List<String> certChain() {
        return certChain;
    }

    @Override
    public String certSha1() {
        return certSha1;
    }

    @Override
    public String certSha256() {
        return certSha256;
    }

    @Override
    public List<String> critical() {
        return critical;
    }
}
