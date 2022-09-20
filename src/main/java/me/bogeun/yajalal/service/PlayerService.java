package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.Player;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerUpdateDto;

public interface PlayerService {

    Player createNewPlayer(Long userId, PlayerCreateDto createDto);

    Player getPlayerById(Long playerId);

    Player getPlayerByName(String playerName);

    Player updatePlayerInfo(Long playerId, PlayerUpdateDto updateDto);

}
