package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.Team;
import me.bogeun.yajalal.payload.league.LeagueCreateDto;
import me.bogeun.yajalal.payload.league.LeagueUpdateDto;

import java.util.List;

public interface LeagueService {

    League createNewLeague(LeagueCreateDto createDto);

    League getLeagueById(Long leagueId);

    List<Team> getTeamListByLeague(League league);

    void updateLeagueInfo(Long leagueId, LeagueUpdateDto updateDto);

    void joinTeam(Long leagueId, Long teamId);

    void leaveTeam(Long leagueId, Long teamId);

    void startLeague(Long leagueId);

    void endLeague(Long leagueId);

    void checkTeamsStatusInLeague(Long teamId);

}
