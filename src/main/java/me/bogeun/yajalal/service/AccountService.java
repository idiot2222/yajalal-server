package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.mapper.AccountMapper;
import me.bogeun.yajalal.payload.AccountJoinDto;
import me.bogeun.yajalal.repository.AccountRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public Account joinNewAccount(AccountJoinDto joinDto) {
        Account account = accountMapper.joinDtoToEntity(joinDto);

        return accountRepository.save(account);
    }
}
