package me.bogeun.yajalal.security.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.bogeun.yajalal.entity.account.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expTime}")
    private Long expTime;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public String generateToken(String subject, Role role) {
        if (expTime <= 0) {
            throw new RuntimeException("invalid expiration time.");
        }

        Key signingKey = getSigningKey();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(Map.of("role", role))
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .signWith(signingKey, signatureAlgorithm)
                .compact();
    }

    public boolean isCorrectToken(String token, String subject) {
        boolean result;

        try {
            result = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()).build()
                    .parseClaimsJws(token).getBody()
                    .getSubject()
                    .equals(subject);
        } catch (Exception e) {
            return false;
        }

        return result;
    }

    public Key getSigningKey() {
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secret);

        return new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
    }

    public String getUsername(String token) {
        return getClaimByName(token, "sub");
    }

    public String getRole(String token) {
        return getClaimByName(token, "role");
    }

    public String getClaimByName(String token, String claimName) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token)
                .getBody()
                .get(claimName, String.class);
    }

}
