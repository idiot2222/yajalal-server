package me.bogeun.yajalal.mapper;

import me.bogeun.yajalal.entity.Player;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayerMapper {

    Player createDtoToEntity(PlayerCreateDto createDto);

    @Mapping(target = "subPositions", ignore = true)
    PlayerInfoDto entityToInfoDto(Player player);
}
