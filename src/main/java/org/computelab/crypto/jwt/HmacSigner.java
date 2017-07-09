package org.computelab.crypto.jwt;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.Mac;

/**
 * Signs using a shared secret. This is usually used by the same service
 * to verify token signed by itself. Or by services on the same trusted
 * network where the key never leaves the network.
 */
public class HmacSigner implements Signer {

    private final Mac mac;
    private final Decoder decoder;

    public HmacSigner(final String algorithm, final Key key) {
        this(algorithm, key, Base64.getUrlDecoder());
    }

    HmacSigner(final String algorithm, final Key key, final Decoder decoder) {
        // TODO: Precondition
        try {
            mac = Mac.getInstance(algorithm);
            mac.init(key);
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
        final byte[] content = rawJws.content().getBytes(StandardCharsets.US_ASCII);
        final byte[] result = mac.doFinal(content);
        final byte[] signature = decoder.decode(rawJws.signature());
        return MessageDigest.isEqual(result, signature);
    }
}
