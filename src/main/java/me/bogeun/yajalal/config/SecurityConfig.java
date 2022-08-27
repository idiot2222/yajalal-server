package me.bogeun.yajalal.config;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.security.filters.FormLoginFilter;
import me.bogeun.yajalal.security.filters.JwtAuthenticationFilter;
import me.bogeun.yajalal.security.handlers.FormLoginFailHandler;
import me.bogeun.yajalal.security.handlers.FormLoginSuccessHandler;
import me.bogeun.yajalal.security.providers.FormLoginProvider;
import me.bogeun.yajalal.security.providers.JwtAuthenticationProvider;
import me.bogeun.yajalal.security.service.AccountDetailsService;
import me.bogeun.yajalal.security.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AccountDetailsService accountDetailsService;

    private final JwtUtils jwtUtils;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        ;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(
                loginProvider(),
                jwtAuthenticationProvider()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    // filters ------------------------
    private FormLoginFilter loginFilter() {
        return new FormLoginFilter(
                "/login",
                authenticationManager(),
                new FormLoginSuccessHandler(jwtUtils),
                new FormLoginFailHandler(),
                accountDetailsService
        );
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() {

        return new JwtAuthenticationFilter(
                new AndRequestMatcher(
                        new NegatedRequestMatcher(new AntPathRequestMatcher("/account/join", "POST")),
                        new NegatedRequestMatcher(new AntPathRequestMatcher("/account/current", "GET")),
                        new NegatedRequestMatcher(new AntPathRequestMatcher("/login", "POST"))
                ),
                authenticationManager()
        );
    }

    // providers ----------------------
    @Bean
    public FormLoginProvider loginProvider() {
        return new FormLoginProvider(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtUtils);
    }
}
