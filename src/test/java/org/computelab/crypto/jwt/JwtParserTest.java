package org.computelab.crypto.jwt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JwtParserTest {

    @Test
    public void test() {
        final String token =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpc3MiOiJodHRwczovL2NvbXB1dGVsYWIub" +
                "3JnL2NhYTcwOTUyLTNhODItNDljYy05YmQ1LW" +
                "RhZjc5NWVlZjcxNi8iLCJzdWIiOiJ0ZXN0Iiw" +
                "iYXVkIjoiaHR0cHM6Ly90ZXN0LmNvbXB1dGVs" +
                "YWIub3JnL2p3dCIsImV4cCI6MTQxNjk3MjU4O" +
                "CwibmJmIjoxNDE2OTY5NTg4LCJpYXQiOjE0MT" +
                "Y5Njk1ODgsImp0aSI6IjYxMWVmMjU3LTlhZmM" +
                "tNGMzZC04MDhmLTFmM2E1OGUwMTg4YiJ9.";
        final JwtParser parser = new JwtParser(BasicJwtHeader.class, BasicJwtPayload.class);
        final Jwt jwt = parser.parse(token);
        assertEquals("JWT", jwt.header().type());
        assertEquals("HS256", jwt.header().algorithm());
        assertEquals("https://computelab.org/caa70952-3a82-49cc-9bd5-daf795eef716/", jwt.payload().issuer());
        assertEquals("test", jwt.payload().subject());
        assertEquals("https://test.computelab.org/jwt", jwt.payload().audience());
        assertEquals(1416972588L, jwt.payload().expiration().longValue());
        assertEquals(1416969588L, jwt.payload().notBefore().longValue());
        assertEquals(1416969588L, jwt.payload().issuedAt().longValue());
        assertEquals("611ef257-9afc-4c3d-808f-1f3a58e0188b", jwt.payload().jwtId());
    }
}
