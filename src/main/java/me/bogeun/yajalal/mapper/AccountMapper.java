package me.bogeun.yajalal.mapper;

import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.payload.AccountJoinDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account joinDtoToEntity(AccountJoinDto joinDto);

}
