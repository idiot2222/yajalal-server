package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.entity.Gender;
import me.bogeun.yajalal.payload.AccountJoinDto;
import me.bogeun.yajalal.payload.CurrentUserDto;
import me.bogeun.yajalal.security.service.CurrentUser;
import me.bogeun.yajalal.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/join")
    public String join(@RequestBody AccountJoinDto joinDto) {
        accountService.joinNewAccount(joinDto);

        return "ok";
    }

    @GetMapping("/current")
    public CurrentUserDto getCurrent(@CurrentUser Account account) {
        if (account == null) {
            return new CurrentUserDto();
        }

        LocalDate birthDate = account.isBirthDatePublic() ? account.getBirthDate() : null;
        Gender gender = account.isGenderPublic() ? account.getGender() : null;

        return CurrentUserDto.builder()
                .username(account.getUsername())
                .email(account.getEmail())
                .birthDate(birthDate)
                .gender(gender)
                .build();
    }

}
