package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.payload.AccountInfoDto;
import me.bogeun.yajalal.payload.AccountJoinDto;
import me.bogeun.yajalal.payload.CurrentUserDto;
import me.bogeun.yajalal.security.service.CurrentUser;
import me.bogeun.yajalal.service.AccountService;
import me.bogeun.yajalal.validator.AccountJoinValidator;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final AccountJoinValidator accountJoinValidator;


    @PostMapping("/join")
    public String join(@RequestBody AccountJoinDto joinDto, Errors errors) {
        accountJoinValidator.validate(joinDto, errors);
        if(errors.hasErrors()) {
            return "error";
        }

        accountService.joinNewAccount(joinDto);

        return "ok";
    }

    @GetMapping("/info/{id}")
    public AccountInfoDto getAccountInfo(@PathVariable Long id) {
        return accountService.getAccountInfoById(id);
    }

    @GetMapping("/current")
    public CurrentUserDto getCurrent(@CurrentUser Account account) {
        if (account == null) {
            return new CurrentUserDto();
        }

        return CurrentUserDto.builder()
                .username(account.getUsername())
                .email(account.getEmail())
                .build();
    }

}
