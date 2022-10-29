package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.payload.stat.StatResponseDto;
import me.bogeun.yajalal.payload.team.TeamCreateDto;
import me.bogeun.yajalal.payload.team.TeamInfoDto;
import me.bogeun.yajalal.payload.team.TeamUpdateDto;

import java.util.List;

public interface TeamService {

    Team createNewTeam(TeamCreateDto createDto);

    Team getTeamByName(String teamName);

    Team getTeamById(Long teamId);

    TeamInfoDto getTeamInfoById(Long teamId);

    void updateTeamInfo(Long id, TeamUpdateDto updateDto);

    List<StatResponseDto> getTeamBattingStats(Long teamId, List<String> stats);

    List<StatResponseDto> getTeamPitchingStats(Long teamId, List<String> stats);

    League getLeagueByTeam(Team team);

    void readyToStartLeague(Long teamId);

    List<Team> getTeamsByLeague(League league);
}
