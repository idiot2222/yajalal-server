package me.bogeun.yajalal.security.providers;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.security.tokens.JwtAuthenticationToken;
import me.bogeun.yajalal.security.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtUtils jwtUtils;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwt = (String) authentication.getPrincipal();
        String username;
        List<SimpleGrantedAuthority> grantedAuthorities;

        try {
            username = jwtUtils.getUsername(jwt);
            grantedAuthorities = List.of(new SimpleGrantedAuthority(jwtUtils.getRole(jwt)));
        }
        catch (Exception e) {
            throw new BadCredentialsException("token expired");
        }

        if (jwtUtils.isCorrectToken(jwt, username)) {
            return JwtAuthenticationToken.authenticatedToken(jwt, grantedAuthorities);
        }

        throw new BadCredentialsException("invalid password.");
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class == authentication;
    }

}
