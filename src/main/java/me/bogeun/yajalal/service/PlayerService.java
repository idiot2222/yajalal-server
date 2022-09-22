package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.Player;
import me.bogeun.yajalal.entity.Position;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerInfoDto;
import me.bogeun.yajalal.payload.player.PlayerUpdateDto;

import java.util.List;

public interface PlayerService {

    Player createNewPlayer(Long userId, PlayerCreateDto createDto);

    Player getPlayerById(Long playerId);

    Player getPlayerByUserId(Long userId);

    PlayerInfoDto getPlayerInfoById(Long playerId);

    PlayerInfoDto getPlayerInfoByUserId(Long userId);

    PlayerInfoDto getPlayerAllInfoByUserId(Long userId);

    Player getPlayerByName(String playerName);

    Player updatePlayerInfo(Long userId, PlayerUpdateDto updateDto);

}
