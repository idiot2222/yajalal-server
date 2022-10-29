package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.player.Player;
import me.bogeun.yajalal.entity.league.Team;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerIdDto;
import me.bogeun.yajalal.payload.player.PlayerInfoDto;
import me.bogeun.yajalal.payload.player.PlayerUpdateDto;
import me.bogeun.yajalal.payload.stat.StatResponseDto;

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

    void joinTheClub(Long playerId, Long teamId);

    Team getTeamByPlayerId(Long playerId);

    League getLeagueByPlayerId(Long playerId);

    List<StatResponseDto> getTopBattersByLeague(League league, int count, String[] stats);

    List<StatResponseDto> getTopPitchersByLeague(League league, int count, String[] stats);

    void setBackNumber(Long playerId, Integer backNumber);

    List<PlayerIdDto> getPlayerAllByTeamId(Long teamId);

}
