package me.bogeun.yajalal.repository;

import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.entity.Role;
import me.bogeun.yajalal.repository.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void getRoleByUsername() {
        Account account = Account.builder()
                .username("test")
                .password("test")
                .email("test@test.com")
                .role(Role.COMMON)
                .build();

        accountRepository.save(account);

        Role role = accountRepository.getRoleByUsername("test");

        assertEquals(Role.COMMON, role);
    }
}