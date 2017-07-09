package org.computelab.crypto.jwt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Base64;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

/**
 * Tests HmacSigned integrated with the rest of the code.
 */
public class HmacSignerIntegTest {

    @Test
    public void test() throws URISyntaxException, IOException {
        final URI uri = getClass().getResource("/hmac-verify.txt").toURI();
        final Path path = Paths.get(uri);
        final List<String> lines = Files.readAllLines(path);
        final String encodedKey = lines.get(0);
        final String compactJws = lines.get(1);
        // RawJws parsing
        final RawJws rawJws = RawJws.parseJwt(compactJws);
        assertEquals("{\"alg\":\"HS512\"}", rawJws.header());
        assertEquals("{\"sub\":\"Test\"}", rawJws.payload());
        assertEquals("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0In0", rawJws.content());
        assertEquals("MBW9tRgFLPnaOLJ_hc8HCedTMi1G0cip98rn-21WxOgD1FrEuEB44iONn-" +
                "LWe1O-XrnliOHl0G71jSYrtrvk_w", rawJws.signature());
        // JwsParser
        final JwtParser jwtParser = new JwtParser();
        final Jws jws = jwtParser.parseJws(compactJws);
        assertNotNull(jws.header());
        assertEquals("HS512", jws.header().algorithm());
        assertNotNull(jws.claims());
        assertNotNull("Test", jws.claims().subject());
        assertNotNull(jws.rawToken());
        // Verify Signature
        final SecretKeySpec ks = new SecretKeySpec(
                Base64.getUrlDecoder().decode(encodedKey), "HmacSHA512");
        final JwsSignatureVerifier verifier = new JwsSignatureVerifier(new KeyProvider() {
            @Override
            public Key getKey(JwsHeader header) {
                return ks;
            }
        });
        verifier.verify(jws);
    }
}
