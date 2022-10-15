package me.bogeun.yajalal.mapper;

import me.bogeun.yajalal.entity.account.Account;
import me.bogeun.yajalal.payload.account.AccountInfoDto;
import me.bogeun.yajalal.payload.account.AccountJoinDto;
import me.bogeun.yajalal.payload.account.CurrentUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account joinDtoToEntity(AccountJoinDto joinDto);

    CurrentUserDto accountToCurrentUserDto(Account account);

    AccountInfoDto accountToInfoDto(Account account);

}
