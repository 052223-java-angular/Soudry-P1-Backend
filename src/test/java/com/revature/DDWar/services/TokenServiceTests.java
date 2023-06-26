package com.revature.DDWar.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.revature.DDWar.dtos.responses.Principal;

public class TokenServiceTests {

    @Test
    public void testGenerateToken() {

        String SECRET_KEY = "c2206ffb-01cf-4a2f-8d76-d4bk3043847b";


        Principal userPrincipal = new Principal();
        userPrincipal.setId("123");
        userPrincipal.setUsername("john.doe");


        String token = generateToken(userPrincipal, SECRET_KEY);

    
        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());

        // Decode and verify the token
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

    
        Assertions.assertEquals(userPrincipal.getId(), claims.get("id", String.class));
        Assertions.assertEquals(userPrincipal.getUsername(), claims.getSubject());


        Assertions.assertTrue(claims.getExpiration().after(new Date()));
    }

    private String generateToken(Principal userPrincipal, String secretKey) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userPrincipal.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Set expiration time to 10 hours
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
