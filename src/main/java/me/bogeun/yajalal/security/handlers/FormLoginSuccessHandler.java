package me.bogeun.yajalal.security.handlers;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Role;
import me.bogeun.yajalal.security.utils.JwtUtils;
import me.bogeun.yajalal.security.service.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FormLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserAccount userDetails = (UserAccount) authentication.getPrincipal();
        Role role = getRole(authentication);

        String token = jwtUtils.generateToken(userDetails.getUsername(), role);

        loginProcessing(authentication);
        responseProcessing(response, token);
    }

    private void loginProcessing(Authentication authentication) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    private void responseProcessing(HttpServletResponse response, String token) throws IOException {
        response.setStatus(200);
        response.getWriter().write("login success");
        response.setHeader("Authorization", token);
    }

    private Role getRole(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(x -> Role.valueOf(x.getAuthority()))
                .collect(Collectors.toList())
                .get(0);
    }

}
