package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.payload.account.AccountInfoDto;
import me.bogeun.yajalal.payload.account.AccountJoinDto;
import me.bogeun.yajalal.payload.account.AccountUpdateDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface AccountService {

    Account joinNewAccount(AccountJoinDto joinDto);

    Account getAccountByUsername(String username);

    Collection<? extends GrantedAuthority> getAuthorities(String username);

    Account getAccountById(Long id);

    AccountInfoDto getAccountInfoById(Long id);

    void updateAccountInfo(Long id, AccountUpdateDto updateDto);

}
