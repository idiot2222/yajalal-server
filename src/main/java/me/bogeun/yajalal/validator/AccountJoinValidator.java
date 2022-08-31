package me.bogeun.yajalal.validator;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.AccountJoinDto;
import me.bogeun.yajalal.repository.account.AccountRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class AccountJoinValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountJoinDto.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountJoinDto dto = (AccountJoinDto) target;

        if(accountRepository.countByUsername(dto.getUsername()) > 0) {
            errors.reject("duplicated username.");
        }
        if(accountRepository.countByNickname(dto.getNickname()) > 0) {
            errors.reject("duplicated nickname.");
        }
        if(accountRepository.countByEmail(dto.getEmail()) > 0) {
            errors.reject("duplicated email.");
        }
    }

}
