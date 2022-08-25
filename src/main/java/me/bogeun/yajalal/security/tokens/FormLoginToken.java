package me.bogeun.yajalal.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class FormLoginToken extends UsernamePasswordAuthenticationToken {

    public FormLoginToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public FormLoginToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static FormLoginToken unauthenticatedToken(Object principal, Object credential) {
        return new FormLoginToken(principal, credential);
    }

    public static FormLoginToken authenticatedToken(Object principal, Object credential, Collection<? extends GrantedAuthority> authorities) {
        return new FormLoginToken(principal, credential, authorities);
    }

}
