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
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import org.junit.Test;

public class RsaSignerIntegTest {

    @Test
    public void test() throws URISyntaxException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        final URI uri = getClass().getResource("/rsa-verify.txt").toURI();
        final Path path = Paths.get(uri);
        final List<String> lines = Files.readAllLines(path);
        final String encodedKey = lines.get(0);
        final String compactJws = lines.get(1);
        // RawJws parsing
        final RawJws rawJws = RawJws.parseJwt(compactJws);
        assertEquals("{\"alg\":\"RS512\"}", rawJws.header());
        assertEquals("{\"sub\":\"Test\"}", rawJws.payload());
        assertEquals("eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJUZXN0In0", rawJws.content());
        assertEquals("gJtyhczDBjJaJ1hRr-qyxMyL-FaxCqesDs2H3jeOlSHjpRQ2r" +
                "jTRrJHkNrMS0593BYUVCApicxUQGsAIY4FKuibq_2AGZ6Y7_iE1ry8" +
                "muUE4XXrgI5WDUVFqdhsIcLW3eOVkgODHytkcSAAytFOVzt5VpiL40" +
                "qhoN_5DXdWGvZyqgFn89EShsJrI5IsczLaD9anl4_iHiWrm95vDs_-" +
                "Wa1NelH-G2-PkeYT5f6AMZE298vKU3PePav7S5AAs6H7_V72cyAIGD" +
                "-lyXdl79Sv_ALe_8Gs1chekSFuH4vIYIr6UiQ2j6QMaBMGCA3Ca6Fb" +
                "C2U2sckxhBqYSUjPY46Iy-MwORsq_w7FBVyEeo-buQgfXjEOQPt-wg" +
                "Gg3bfH7WtWNDW9mUevHEJDH-KKjwM6WWECIzAJIZ0UZgFNmZWa5L1L" +
                "OTeFroqa7jkkhuif3-yEyiK0zADH7v41VQdAOFjm5YDgiwyO7UGxCC" +
                "s_vIBtOhw4kCvYu9u9TDlreHGCbl2cF-elDAlnUDHRRvvrlNoDP-iz" +
                "5gp02-a5tyHlZnpBRIlTDe7SxqRzYhk7_6xhq0Trv8MtPET23Qr-CZ" +
                "KNIu0OzACM8l_RLZaM19xNf51lWqv5FeOeIAKn7OYCDD5H0F7dZchJ" +
                "h9ZrwK_tLABOZQpb0u8y6IWC9DibJJjvHz35Pqqg", rawJws.signature());
        // JwsParser
        final JwtParser jwtParser = new JwtParser();
        final Jws jws = jwtParser.parseJws(compactJws);
        assertNotNull(jws.header());
        assertEquals("RS512", jws.header().algorithm());
        assertNotNull(jws.claims());
        assertNotNull("Test", jws.claims().subject());
        assertNotNull(jws.rawToken());
        // Verify Signature
        final X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(
                Base64.getUrlDecoder().decode(encodedKey));
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        final JwsSignatureVerifier verifier = new JwsSignatureVerifier(new KeyProvider() {
            @Override
            public Key getKey(JwsHeader header) {
                try {
                    return keyFactory.generatePublic(pubKeySpec);
                } catch (InvalidKeySpecException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        verifier.verify(jws);
    }
}
