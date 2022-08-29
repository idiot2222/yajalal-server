package me.bogeun.yajalal.security.utils;

import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class NorRequestMatcher implements RequestMatcher {

    private final OrRequestMatcher matcher;

    public NorRequestMatcher(RequestMatcher... requestMatchers) {
        this.matcher = new OrRequestMatcher(requestMatchers);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return !matcher.matches(request);
    }
}
