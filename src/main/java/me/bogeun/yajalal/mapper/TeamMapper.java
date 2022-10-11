package me.bogeun.yajalal.mapper;


import me.bogeun.yajalal.entity.Team;
import me.bogeun.yajalal.payload.team.TeamCreateDto;
import me.bogeun.yajalal.payload.team.TeamInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamMapper {

    Team createDtoToEntity(TeamCreateDto createDto);

    TeamInfoDto entityToInfoDto(Team team);

}
