package me.bogeun.yajalal.repository.team;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.Team;

public interface TeamDynamicRepository {

    League findLeagueByTeam(Team t);

}
