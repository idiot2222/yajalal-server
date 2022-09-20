package me.bogeun.yajalal.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.bogeun.yajalal.payload.account.LoginDto;
import me.bogeun.yajalal.security.handlers.FormLoginFailHandler;
import me.bogeun.yajalal.security.handlers.FormLoginSuccessHandler;
import me.bogeun.yajalal.security.service.AccountDetailsService;
import me.bogeun.yajalal.security.service.UserAccount;
import me.bogeun.yajalal.security.tokens.FormLoginToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final FormLoginSuccessHandler formLoginSuccessHandler;
    private final FormLoginFailHandler formLoginFailHandler;

    private final AccountDetailsService accountDetailsService;


    public FormLoginFilter(RequestMatcher requestMatcher,
                           AuthenticationManager authenticationManager,
                           FormLoginSuccessHandler formLoginSuccessHandler,
                           FormLoginFailHandler formLoginFailHandler,
                           AccountDetailsService accountDetailsService) {

        super(requestMatcher, authenticationManager);

        this.formLoginSuccessHandler = formLoginSuccessHandler;
        this.formLoginFailHandler = formLoginFailHandler;
        this.accountDetailsService = accountDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        LoginDto dto = new ObjectMapper().readValue(request.getReader(), LoginDto.class);
        UserAccount userDetails = (UserAccount) accountDetailsService.loadUserByUsername(dto.getUsername());
        String password = dto.getPassword();

        FormLoginToken token = FormLoginToken.unauthenticatedToken(userDetails, password);

        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        formLoginSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        formLoginFailHandler.onAuthenticationFailure(request, response, failed);
    }

}
