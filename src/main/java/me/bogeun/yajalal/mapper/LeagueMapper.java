package me.bogeun.yajalal.mapper;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.payload.league.LeagueDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LeagueMapper {

    League dtoToEntity(LeagueDto leagueDto);

    LeagueDto entityToDto(League league);

}