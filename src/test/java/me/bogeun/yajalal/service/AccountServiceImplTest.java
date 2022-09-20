package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.entity.Gender;
import me.bogeun.yajalal.payload.AccountJoinDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Test
    void join() {
        AccountJoinDto dto = new AccountJoinDto();
        dto.setUsername("test");
        dto.setPassword("password");
        dto.setEmail("test@email.com");
        dto.setBirthDate(LocalDate.of(1995, 5, 30));
        dto.setGender(Gender.MALE);

        Account account = accountServiceImpl.joinNewAccount(dto);

        assertEquals(dto.getUsername(), account.getUsername());
        assertEquals(dto.getPassword(), account.getPassword());
        assertEquals(dto.getEmail(), account.getEmail());
        assertEquals(dto.getBirthDate(), account.getBirthDate());
        assertEquals(dto.getGender(), account.getGender());
    }

}