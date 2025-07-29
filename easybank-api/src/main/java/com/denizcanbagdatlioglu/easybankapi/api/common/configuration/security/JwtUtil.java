package com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

public class JwtUtil {

    private final SecretKey SIGNING_KEY = Jwts.SIG.HS256.key().build();

    private final int EXPIRATION_HOURS = 10;

    private final long MILLISECONDS_IN_ONE_HOUR = 3_600_000L;

    public String generateJwtToken(String username) {
        Date now = Date.from(Instant.now());
        Date expirationDate = getExpirationDate(now, EXPIRATION_HOURS);
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(SIGNING_KEY)
                .compact();
    }

    public boolean isTokenValid(String token, String username) {
        Claims claims = extractClaims(token);
        return isSubjectValid(claims, username) && isExpirationDateValid(claims);
    }

    public String getUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private boolean isSubjectValid(Claims claims, String expectedSubject) {
        return claims.getSubject().equals(expectedSubject);
    }

    private boolean isExpirationDateValid(Claims claims) {
        Date now = Date.from(Instant.now());
        return claims.getExpiration().after(now);
    }

    private Date getExpirationDate(Date now, int hours) {
        long expirationTime = MILLISECONDS_IN_ONE_HOUR * (long)hours;
        return new Date(now.getTime() + expirationTime);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(SIGNING_KEY).build().parseSignedClaims(token).getPayload();
    }

}
