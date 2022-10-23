package me.bogeun.yajalal.repository.team;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.Team;

import java.util.List;

public interface TeamDynamicRepository {

    League findLeagueByTeam(Team t);

    List<Team> findTeamListByLeague(League league);

    Integer findWinCount(Team t);

    Integer findLoseCount(Team t);

    Integer findDrawCount(Team t);

}
