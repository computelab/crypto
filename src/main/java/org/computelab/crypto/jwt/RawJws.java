package org.computelab.crypto.jwt;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.regex.Pattern;

import com.google.common.base.CharMatcher;

/**
 * Unpacks JWS into raw JSON.
 *
 * See https://tools.ietf.org/html/rfc7515#section-5 Producing and Consuming JWSs.
 * See https://tools.ietf.org/html/rfc4648#section-5 Base64 URL encoding.
 */
public class RawJws {

    // === Static Members ===

    private static final Character DOT = Character.valueOf('.');
    private static final Pattern SPLIT = Pattern.compile("\\" + DOT);

    /*
     * Decoder is thread-safe. The decoder is a base64url decoder.
     * Note this is not the same as basic base64 encoding or URL encoding.
     * It is a modified base64 encoding that's URL safe.
     */
    private static final Decoder DECODER = Base64.getUrlDecoder();

    public static RawJws parseJwt(final String jwt) {
        checkNotNull(jwt);
        checkArgument(!jwt.isEmpty());
        checkArgument(CharMatcher.is(DOT).countIn(jwt) == 2);
        final String[] parts = SPLIT.split(jwt, 3);
        final String header = decode(parts[0]);
        final String payload = decode(parts[1]);
        final String content = parts[0] + DOT + parts[1];
        final String signature = parts[2];
        return new RawJws(header, payload, content, signature);
    }

    private static String decode(final String encoded) {
        return new String(DECODER.decode(encoded), StandardCharsets.UTF_8);
    }

    // === Non-Static Members ===

    private final String header;
    private final String payload;
    private final String content;
    private final String signature;

    // Package scope to NOT to expose dependencies to gson
    RawJws(final String header, final String payload,
            final String content, final String signature) {
        checkNotNull(header);
        checkNotNull(payload);
        checkNotNull(content);
        checkNotNull(signature);
        this.header = header;
        this.payload = payload;
        this.content = content;
        this.signature = signature;
    }

    /**
     * The JOSE header as raw JSON string.
     */
    public String header() {
        return header;
    }

    /**
     * The JWS payload as raw JSON string.
     */
    public String payload() {
        return payload;
    }

    /**
     * The header concatenated with dot and then with the payload.
     * The header and the payload are base64 URL encoded. The dot
     * is not encoded. This is the input to the signing algorithm.
     */
    public String content() {
        return content;
    }

    /**
     * The base64url encoded signature.
     */
    public String signature() {
        return signature;
    }
}
