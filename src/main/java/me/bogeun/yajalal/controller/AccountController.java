package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.payload.AccountJoinDto;
import me.bogeun.yajalal.payload.LoginDto;
import me.bogeun.yajalal.security.service.CurrentUser;
import me.bogeun.yajalal.security.service.UserAccount;
import me.bogeun.yajalal.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String token = accountService.login(loginDto);

        return ResponseEntity.ok()
                .header("Authorization", token)
                .body("ok");
    }

    @GetMapping("/current")
    public Account getCurrent(@CurrentUser Account account) {
        return account;
    }

}
