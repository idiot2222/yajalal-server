package me.bogeun.yajalal.mapper;

import me.bogeun.yajalal.entity.Player;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerInfoDto;
import me.bogeun.yajalal.payload.player.PlayerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayerMapper {

    Player createDtoToEntity(PlayerCreateDto createDto);

    PlayerInfoDto entityToInfoDto(Player player);
}
