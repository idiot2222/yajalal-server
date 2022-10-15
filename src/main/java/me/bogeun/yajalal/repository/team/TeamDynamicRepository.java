package me.bogeun.yajalal.repository.team;

import me.bogeun.yajalal.entity.League;
import me.bogeun.yajalal.entity.Team;

public interface TeamDynamicRepository {

    League findLeagueByTeam(Team t);

}
