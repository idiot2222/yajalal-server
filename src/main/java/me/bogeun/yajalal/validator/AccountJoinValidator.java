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

        if(accountRepository.existsByUsername(dto.getUsername())) {
            errors.reject("duplicated username.");
        }
        if(accountRepository.existsByNickname(dto.getNickname())) {
            errors.reject("duplicated nickname.");
        }
        if(accountRepository.existsByEmail(dto.getEmail())) {
            errors.reject("duplicated email.");
        }
    }

}
