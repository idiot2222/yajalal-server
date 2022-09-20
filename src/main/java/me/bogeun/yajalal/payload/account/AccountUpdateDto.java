package me.bogeun.yajalal.payload.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateDto {

    private String nickname;

    private String password;

}
