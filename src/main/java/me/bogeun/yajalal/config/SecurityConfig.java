package me.bogeun.yajalal.config;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.security.service.AccountDetailsService;
import me.bogeun.yajalal.security.utils.JwtUtils;
import me.bogeun.yajalal.security.filters.FormLoginFilter;
import me.bogeun.yajalal.security.handlers.FormLoginFailHandler;
import me.bogeun.yajalal.security.handlers.FormLoginSuccessHandler;
import me.bogeun.yajalal.security.providers.FormLoginProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .rememberMe()
                .and()
                .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(loginProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    // filters ------------------------
    public FormLoginFilter loginFilter() throws Exception {
        return new FormLoginFilter(
                "/login",
                authenticationManager(),
                new FormLoginSuccessHandler(jwtUtils),
                new FormLoginFailHandler(),
                accountDetailsService
        );
    }

    // providers ----------------------
    @Bean
    public FormLoginProvider loginProvider() {
        return new FormLoginProvider(passwordEncoder());
    }

}
