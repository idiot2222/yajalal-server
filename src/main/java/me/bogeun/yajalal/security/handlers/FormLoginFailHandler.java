package me.bogeun.yajalal.security.handlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        responseProcessing(response);
    }

    private void responseProcessing(HttpServletResponse response) throws IOException {
        response.setStatus(403);
        response.getWriter().write("login fail");
    }

}
