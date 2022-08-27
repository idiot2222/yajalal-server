package me.bogeun.yajalal.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private JwtAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    private JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static JwtAuthenticationToken unauthenticatedToken(Object principal) {
        return new JwtAuthenticationToken(principal, null);
    }

    public static JwtAuthenticationToken authenticatedToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        return new JwtAuthenticationToken(principal, null, authorities);
    }

}
