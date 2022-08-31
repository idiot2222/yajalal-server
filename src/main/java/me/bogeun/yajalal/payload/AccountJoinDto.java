package me.bogeun.yajalal.payload;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.Gender;
import me.bogeun.yajalal.entity.Role;

import java.time.LocalDate;

@Getter
@Setter
public class AccountJoinDto {

    private String username;

    private String password;

    private String email;

    private LocalDate birthDate;

    private Gender gender;

    private Role role;

}
