package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.entity.Authority;
import me.bogeun.yajalal.mapper.AccountMapper;
import me.bogeun.yajalal.payload.AccountJoinDto;
import me.bogeun.yajalal.payload.LoginDto;
import me.bogeun.yajalal.repository.AccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final AccountMapper accountMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account;

        try {
            account = getAccountByUsername(username);
        } catch (IllegalArgumentException e) {
            throw new UsernameNotFoundException("not exist username.");
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getAuthority().toString())
                .build();
    }


    public Account joinNewAccount(AccountJoinDto joinDto) {
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));

        Account account = accountMapper.joinDtoToEntity(joinDto);
        account.setAuthority(Authority.COMMON);

        return accountRepository.save(account);
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("invalid username."));
    }

    public String login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("invalid login info.");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return loginDto.getUsername();
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
