package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.AccountJoinDto;
import me.bogeun.yajalal.payload.LoginDto;
import me.bogeun.yajalal.security.JwtUtils;
import me.bogeun.yajalal.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    private final JwtUtils jwtUtils;


    @PostMapping("/join")
    public String join(@RequestBody AccountJoinDto joinDto) {
        accountService.joinNewAccount(joinDto);

        return "ok";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String result = accountService.login(loginDto);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<Principal> logout(Principal principal) {
        accountService.logout();

        return ResponseEntity
                .status(200)
                .body(principal);
    }

    @GetMapping("/current")
    public ResponseEntity<Principal> current(Principal principal) {
        return ResponseEntity
                .status(200)
                .body(principal);
    }

}
