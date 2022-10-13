package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.Team;
import me.bogeun.yajalal.payload.team.PlayerStatResponse;
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

    List<PlayerStatResponse> getTeamBattingStats(Long teamId, List<String> stats);

    List<PlayerStatResponse> getTeamPitchingStats(Long teamId, List<String> stats);
}
