package org.computelab.crypto.jwt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class RsaSigner implements Signer {

    private final Signature signature;

    public RsaSigner(final String algorithm, final PublicKey publicKey) {
        try {
            signature = Signature.getInstance(algorithm);
            signature.initVerify(publicKey);
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
            signature.update(Base64.getDecoder().decode(rawJws.content()));
            return signature.verify(Base64.getDecoder().decode(rawJws.signature()));
        } catch (SignatureException e) {
            // TODO
            throw new RuntimeException();
        }
    }
}
