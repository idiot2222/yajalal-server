package me.bogeun.yajalal.security.providers;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.security.service.UserAccount;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
public class FormLoginProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        UserAccount userDetails = (UserAccount) token.getPrincipal();
        Account account = userDetails.getAccount();
        String password = (String) token.getCredentials();

        if(isCorrectPassword(account, password)) {
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRole().toString()));

            return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
        }

        throw new BadCredentialsException("invalid login info.");
    }

    private boolean isCorrectPassword(Account account, String password) {
        return passwordEncoder.matches(password, account.getPassword());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
