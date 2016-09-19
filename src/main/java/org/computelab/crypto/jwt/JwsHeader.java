package org.computelab.crypto.jwt;

import java.util.List;

public interface JwsHeader extends JwtHeader {

    String algorithm();

    String keySetUrl();

    String key();

    String certUrl();

    String certSha1();

    String certSha256();

    List<String> certChain();

    String keyId();

    String type();

    String contentType();

    String critical();
}
