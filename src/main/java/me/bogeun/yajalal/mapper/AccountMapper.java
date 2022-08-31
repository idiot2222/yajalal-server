package me.bogeun.yajalal.mapper;

import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.payload.AccountInfoDto;
import me.bogeun.yajalal.payload.AccountJoinDto;
import me.bogeun.yajalal.payload.CurrentUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isBirthDatePublic", source = "birthDatePublic")
    @Mapping(target = "isGenderPublic", source = "genderPublic")
    Account joinDtoToEntity(AccountJoinDto joinDto);

    CurrentUserDto accountToCurrentUserDto(Account account);

    AccountInfoDto accountToInfoDto(Account account);
}
