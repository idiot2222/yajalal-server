package me.bogeun.yajalal.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.bogeun.yajalal.payload.LoginDto;
import me.bogeun.yajalal.security.service.AccountDetailsService;
import me.bogeun.yajalal.security.service.UserAccount;
import me.bogeun.yajalal.security.handlers.FormLoginFailHandler;
import me.bogeun.yajalal.security.handlers.FormLoginSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final FormLoginSuccessHandler formLoginSuccessHandler;
    private final FormLoginFailHandler formLoginFailHandler;

    private final AccountDetailsService accountDetailsService;


    public FormLoginFilter(String defaultFilterProcessesUrl,
                           AuthenticationManager authenticationManager,
                           FormLoginSuccessHandler formLoginSuccessHandler,
                           FormLoginFailHandler formLoginFailHandler,
                           AccountDetailsService accountDetailsService) {

        super(defaultFilterProcessesUrl, authenticationManager);

        this.formLoginSuccessHandler = formLoginSuccessHandler;
        this.formLoginFailHandler = formLoginFailHandler;
        this.accountDetailsService = accountDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        LoginDto dto = new ObjectMapper().readValue(request.getReader(), LoginDto.class);
        UserAccount userDetails = (UserAccount) accountDetailsService.loadUserByUsername(dto.getUsername());
        String password = dto.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password);

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
