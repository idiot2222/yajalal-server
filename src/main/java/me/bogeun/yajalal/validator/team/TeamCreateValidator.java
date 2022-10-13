package me.bogeun.yajalal.validator.team;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.team.TeamCreateDto;
import me.bogeun.yajalal.repository.team.TeamRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class TeamCreateValidator implements Validator {

    private final TeamRepository teamRepository;

    @Override
    public void validate(Object target, Errors errors) {
        TeamCreateDto createDto = (TeamCreateDto) target;

        if(teamRepository.existsByName(createDto.getName())) {
            errors.reject("duplicated team name.");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TeamCreateDto.class == clazz;
    }

}
