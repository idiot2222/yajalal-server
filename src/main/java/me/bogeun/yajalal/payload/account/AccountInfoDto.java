package me.bogeun.yajalal.payload.account;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.account.Gender;
import me.bogeun.yajalal.entity.account.Role;

import java.time.LocalDate;

@Setter
@Getter
public class AccountInfoDto {

    private String username;

    private String nickname;

    private String email;

    private LocalDate birthDate;

    private Gender gender;

    private Role role;

}
