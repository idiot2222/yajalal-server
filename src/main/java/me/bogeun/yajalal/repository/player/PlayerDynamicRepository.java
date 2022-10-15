package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.league.Team;
import me.bogeun.yajalal.payload.team.PlayerStat;

import java.util.List;

public interface PlayerDynamicRepository {

    Team getTeamByPlayerId(Long playerId);

    List<PlayerStat> getTopBatterByTeam(Long teamId, String stat, int n);

    List<PlayerStat> getTopPitcherByTeam(Long teamId, String stat, int n);

}
