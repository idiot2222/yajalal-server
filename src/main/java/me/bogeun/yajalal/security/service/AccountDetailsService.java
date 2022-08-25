package me.bogeun.yajalal.security.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.repository.account.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account;

        try {
            account = getAccountByUsername(username);
        } catch (IllegalArgumentException e) {
            throw new UsernameNotFoundException("not exist username.");
        }

        return new UserAccount(account);
    }

    private Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("invalid username."));
    }

}
