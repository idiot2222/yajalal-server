package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.entity.Role;
import me.bogeun.yajalal.mapper.AccountMapper;
import me.bogeun.yajalal.payload.AccountJoinDto;
import me.bogeun.yajalal.repository.account.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final AccountMapper accountMapper;


    public Account joinNewAccount(AccountJoinDto joinDto) {
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));

        Account account = accountMapper.joinDtoToEntity(joinDto);
        account.setRole(Role.COMMON);

        return accountRepository.save(account);
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("invalid username."));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String username) {
        Role role = accountRepository.getRoleByUsername(username);

        return List.of(new SimpleGrantedAuthority(role.toString()));
    }
}
