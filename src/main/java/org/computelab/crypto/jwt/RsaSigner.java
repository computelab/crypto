package org.computelab.crypto.jwt;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Base64.Decoder;

public class RsaSigner implements Signer {

    private final Signature signature;
    private final Decoder decoder;

    public RsaSigner(final String algorithm, final PublicKey publicKey) {
        this(algorithm, publicKey, Base64.getUrlDecoder());
    }

    RsaSigner(final String algorithm, final PublicKey publicKey, final Decoder decoder) {
        try {
            signature = Signature.getInstance(algorithm);
            signature.initVerify(publicKey);
            this.decoder = decoder;
        } catch (NoSuchAlgorithmException e) {
            // TODO
            throw new RuntimeException();
        } catch (InvalidKeyException e) {
            // TODO
            throw new RuntimeException();
        }
    }

    @Override
    public boolean verify(final RawJws rawJws) {
        try {
            signature.update(rawJws.content().getBytes(StandardCharsets.US_ASCII));
            return signature.verify(decoder.decode(rawJws.signature()));
        } catch (SignatureException e) {
            // TODO
            throw new RuntimeException();
        }
    }
}
