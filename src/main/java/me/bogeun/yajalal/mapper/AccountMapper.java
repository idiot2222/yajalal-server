package me.bogeun.yajalal.mapper;

import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.payload.account.AccountInfoDto;
import me.bogeun.yajalal.payload.account.AccountJoinDto;
import me.bogeun.yajalal.payload.account.CurrentUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account joinDtoToEntity(AccountJoinDto joinDto);

    CurrentUserDto accountToCurrentUserDto(Account account);

    AccountInfoDto accountToInfoDto(Account account);
}
