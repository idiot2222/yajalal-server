package me.bogeun.yajalal.security.service;

import lombok.Getter;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.repository.account.AccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserAccount extends User {

    private final Account account;

    public UserAccount(Account account) {
        super(account.getUsername(), account.getPassword(), List.of(new SimpleGrantedAuthority(account.getRole().toString())));

        this.account = account;
    }

}
