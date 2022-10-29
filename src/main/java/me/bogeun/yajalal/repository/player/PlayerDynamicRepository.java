package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.payload.stat.PlayerStat;

import java.util.List;

public interface PlayerDynamicRepository {

    Team getTeamByPlayerId(Long playerId);

    List<PlayerStat> getTopBatterByTeam(Long teamId, String stat, int n);

    List<PlayerStat> getTopPitcherByTeam(Long teamId, String stat, int n);

    List<PlayerStat> findTopBattersStatByLeague(League league, String stat, int count);

    List<PlayerStat> findTopPitchersStatByLeague(League league, String stat, int count);
}
