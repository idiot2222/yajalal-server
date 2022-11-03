package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.account.Account;
import me.bogeun.yajalal.entity.account.Gender;
import me.bogeun.yajalal.payload.account.AccountJoinDto;
import me.bogeun.yajalal.service.account.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class AccountServiceImplBaboTest {

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Test
    void join() {
        AccountJoinDto dto = AccountJoinDto.builder()
                .username("test")
                .password("password")
                .email("test@email.com")
                .birthDate(LocalDate.of(1995, 5, 30))
                .gender(Gender.MALE)
                .build();

        Account account = accountServiceImpl.joinNewAccount(dto);

        assertEquals(dto.getUsername(), account.getUsername());
        assertEquals(dto.getPassword(), account.getPassword());
        assertEquals(dto.getEmail(), account.getEmail());
        assertEquals(dto.getBirthDate(), account.getBirthDate());
        assertEquals(dto.getGender(), account.getGender());
    }

}