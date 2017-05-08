package org.computelab.crypto.jwt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;

/**
 * Signs using a shared secret. This is usually used by the same service
 * to verify token signed by itself. Or by services on the same trusted
 * network where the key never leaves the network.
 */
public class HmacSigner implements Signer {

    private final Mac mac;

    public HmacSigner(final String algorithm, final Key key) {
        // TODO: Precondition
        try {
            mac = Mac.getInstance(algorithm);
            mac.init(key);
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
        // TODO: Is basic Base64 good fit for JSON?
        // TODO: Remove dependency to Bouncy Castle
        final byte[] content = Base64.getDecoder().decode(rawJws.content());
        final byte[] result = mac.doFinal(content);
        final byte[] signature = Base64.getDecoder().decode(rawJws.signature());
        if (result.length != signature.length) {
            return false;
        }
        return MessageDigest.isEqual(result, signature);
    }
}
