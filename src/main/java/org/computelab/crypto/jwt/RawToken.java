package org.computelab.crypto.jwt;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.nio.charset.StandardCharsets;

import org.bouncycastle.util.encoders.Base64;

import com.google.common.base.CharMatcher;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Decoded JWT in raw JSON.
 */
public class RawToken {

    public static RawToken parse(final String jwt) {

        checkNotNull(jwt);
        checkArgument(!jwt.isEmpty());
        checkArgument(CharMatcher.is('.').countIn(jwt) == 2);

        final String[] parts = jwt.split("\\.", 3);
        final JsonParser parser = new JsonParser();
        final JsonElement header = parser.parse(decode(parts[0]));
        final JsonElement payload = parser.parse(decode(parts[1]));
        final String signature = parts[2];

        return new RawToken(header, payload, signature);
    }

    private static String decode(final String encoded) {
        return new String(Base64.decode(encoded), StandardCharsets.UTF_8);
    }

    private final JsonElement header;
    private final JsonElement payload;
    private final String signature;

    RawToken(JsonElement header, JsonElement payload, String signature) {
        checkNotNull(header);
        checkNotNull(payload);
        checkNotNull(signature);
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    public JsonElement header() {
        return header;
    }

    public JsonElement payload() {
        return payload;
    }

    /**
     * Base64-encoded signature.
     */
    public String signature() {
        return signature;
    }
}
