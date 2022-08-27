package me.bogeun.yajalal.security.providers;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.security.tokens.JwtAuthenticationToken;
import me.bogeun.yajalal.security.utils.JwtUtils;
import me.bogeun.yajalal.service.AccountService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtUtils jwtUtils;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        String username = jwtUtils.getUsername(token);
        List<SimpleGrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(jwtUtils.getRole(token)));

        if (jwtUtils.isCorrectToken(token, username)) {
            return JwtAuthenticationToken.authenticatedToken(token, grantedAuthorities);
        }

        throw new AuthenticationServiceException("invalid password.");
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class == authentication;
    }

}
