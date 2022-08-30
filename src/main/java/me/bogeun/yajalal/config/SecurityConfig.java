package me.bogeun.yajalal.config;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.security.filters.FormLoginFilter;
import me.bogeun.yajalal.security.filters.JwtAuthenticationFilter;
import me.bogeun.yajalal.security.handlers.FormLoginFailHandler;
import me.bogeun.yajalal.security.handlers.FormLoginSuccessHandler;
import me.bogeun.yajalal.security.handlers.NoRedirectLogoutSuccessHandler;
import me.bogeun.yajalal.security.providers.FormLoginProvider;
import me.bogeun.yajalal.security.providers.JwtAuthenticationProvider;
import me.bogeun.yajalal.security.service.AccountDetailsService;
import me.bogeun.yajalal.security.utils.JwtUtils;
import me.bogeun.yajalal.security.utils.NorRequestMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AccountDetailsService accountDetailsService;

    private final JwtUtils jwtUtils;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .logoutSuccessHandler(new NoRedirectLogoutSuccessHandler())
                .and()
                .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

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
                new AntPathRequestMatcher("/account/login", "POST"),
                authenticationManager(),
                new FormLoginSuccessHandler(jwtUtils),
                new FormLoginFailHandler(),
                accountDetailsService
        );
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() {
        NorRequestMatcher pathToSkipMatcher = new NorRequestMatcher(
                new AntPathRequestMatcher("/account/join", "POST"),
                new AntPathRequestMatcher("/account/current", "GET"),
                new AntPathRequestMatcher("/account/login", "POST"),
                new AntPathRequestMatcher("/account/logout", "POST")
        );

        return new JwtAuthenticationFilter(
                pathToSkipMatcher,
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
