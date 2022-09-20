package me.bogeun.yajalal.validator;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.account.AccountUpdateDto;
import me.bogeun.yajalal.repository.account.AccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class AccountUpdateValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountUpdateDto.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountUpdateDto dto = (AccountUpdateDto) target;
        String nickname = dto.getNickname();
        String password = dto.getPassword();

        if(!nickname.isEmpty() && accountRepository.existsByNickname(nickname)) {
            errors.reject("duplicated nickname.");
        }
        if (nickname.length() > 20) {
            errors.reject("invalid nickname.");
        }

        int passwordLength = password.length();
        if(passwordLength != 0 && passwordLength < 10) {
            errors.reject("invalid password.");
        }
    }
}
