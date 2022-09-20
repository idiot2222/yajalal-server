package me.bogeun.yajalal.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.mapper.AccountMapper;
import me.bogeun.yajalal.payload.account.CurrentUserDto;
import me.bogeun.yajalal.security.utils.JwtUtils;
import me.bogeun.yajalal.security.service.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FormLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtils jwtUtils;
    private final AccountMapper accountMapper;

    private final ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserAccount userDetails = (UserAccount) authentication.getPrincipal();
        Account account = userDetails.getAccount();

        loginProcessing(authentication);
        responseProcessing(response, account);
    }

    private void loginProcessing(Authentication authentication) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    private void responseProcessing(HttpServletResponse response, Account account) throws IOException {
        String token = jwtUtils.generateToken(account.getUsername(), account.getRole());
        String refreshToken = jwtUtils.generateRefreshToken(account.getUsername(), account.getRole());
        CurrentUserDto dto = accountMapper.accountToCurrentUserDto(account);

        response.setStatus(200);
        response.setHeader("Authorization", token);
        response.setHeader("RefreshToken", refreshToken);
        response.setHeader("Access-Control-Expose-Headers", "Authorization, RefreshToken");

        response.getWriter().write(objectMapper.writeValueAsString(dto));
    }

}
