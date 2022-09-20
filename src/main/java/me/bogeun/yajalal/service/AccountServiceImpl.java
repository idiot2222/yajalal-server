package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.entity.Role;
import me.bogeun.yajalal.mapper.AccountMapper;
import me.bogeun.yajalal.payload.account.AccountInfoDto;
import me.bogeun.yajalal.payload.account.AccountJoinDto;
import me.bogeun.yajalal.payload.account.AccountUpdateDto;
import me.bogeun.yajalal.repository.account.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final AccountMapper accountMapper;


    @Override
    public Account joinNewAccount(AccountJoinDto joinDto) {
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));

        Account account = accountMapper.joinDtoToEntity(joinDto);
        account.setRole(Role.COMMON);

        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("invalid username."));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(String username) {
        Role role = accountRepository.getRoleByUsername(username);

        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid user id."));
    }

    @Override
    public AccountInfoDto getAccountInfoById(Long id) {
        Account account = getAccountById(id);

        return accountMapper.accountToInfoDto(account);
    }

    @Override
    public void updateAccountInfo(Long id, AccountUpdateDto updateDto) {
        Account account = getAccountById(id);
        String nickname = updateDto.getNickname();
        String password = updateDto.getPassword();

        if(nickname.length() != 0) {
            account.setNickname(updateDto.getNickname());
        }
        if(password.length() != 0) {
            account.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }

        accountRepository.save(account);
    }
}
