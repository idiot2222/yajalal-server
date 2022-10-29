package me.bogeun.yajalal.security;

import me.bogeun.yajalal.entity.account.Role;
import me.bogeun.yajalal.security.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilsBaboTest {

    @Autowired
    JwtUtils jwtUtils;

    String subject;
    Role role;
    String token;

    @BeforeEach
    void beforeEach() {
        subject = "test";
        role = Role.COMMON;
        token = jwtUtils.generateToken(subject, role);
    }

    @Test
    void generateToken() {
        String resultSubject = jwtUtils.getUsername(token);
        String resultRole = jwtUtils.getClaimByName(token, "role");

        assertEquals(subject, resultSubject);
        assertEquals(role, Role.valueOf(resultRole));
    }

    @Test
    void isCorrectToken_pass() {
        boolean result = jwtUtils.isCorrectToken(token, subject);

        assertTrue(result);
    }

    @Test
    void isCorrectToken_fail() {
        String wrongSubject = "fail";
        String infectedToken = getInfectedToken();

        boolean result1 = jwtUtils.isCorrectToken(token, wrongSubject);
        boolean result2 = jwtUtils.isCorrectToken(infectedToken, subject);

        assertFalse(result1);
        assertFalse(result2);
    }

    private String getInfectedToken() {
        String wrongPayload = "{\"role\":\"" + role + "\",\"sub\":\"" + subject + "\",\"exp\":1000000}";
        byte[] encodedPayload = Base64.getEncoder().encode(wrongPayload.getBytes(StandardCharsets.UTF_8));
        String infectedPayload = new String(encodedPayload).substring(0, encodedPayload.length - 1);

        String[] split = token.split("\\.");

        return split[0] + "." +
                infectedPayload + "." +
                split[2];
    }

}