package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.Team;
import me.bogeun.yajalal.entity.league.TeamStatus;
import me.bogeun.yajalal.mapper.TeamMapper;
import me.bogeun.yajalal.payload.team.*;
import me.bogeun.yajalal.repository.player.PlayerRepository;
import me.bogeun.yajalal.repository.team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    private final TeamMapper teamMapper;

    @Override
    public Team createNewTeam(TeamCreateDto createDto) {
        Team team = teamMapper.createDtoToEntity(createDto);
        team.setTeamStatus(TeamStatus.NOT_JOINING);

        return teamRepository.save(team);
    }

    @Override
    public Team getTeamByName(String teamName) {
        return teamRepository.findByName(teamName).orElseThrow(() -> new NoSuchElementException("invalid team name."));
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new NoSuchElementException("invalid team id."));
    }

    @Override
    public TeamInfoDto getTeamInfoById(Long teamId) {
        Team team = getTeamById(teamId);

        return teamMapper.entityToInfoDto(team);
    }

    @Override
    public void updateTeamInfo(Long id, TeamUpdateDto updateDto) {
        Team team = getTeamById(id);
        String name = updateDto.getName();
        String description = updateDto.getDescription();
        Integer limit = updateDto.getLimitOfPlayer();

        if (!name.isBlank()) {
            team.setName(name);
        }
        if (!description.isBlank()) {
            team.setDescription(description);
        }
        team.setLimitOfPlayer(limit);

        teamRepository.save(team);
    }

    @Override
    public List<PlayerStatResponse> getTeamBattingStats(Long teamId, List<String> stats) {
        List<PlayerStatResponse> result = new ArrayList<>();

        for (String stat : stats) {
            List<PlayerStat> resultList = playerRepository.getTopBatterByTeam(teamId, stat, 5);

            PlayerStatResponse playerStatResponse = new PlayerStatResponse(stat, resultList);

            result.add(playerStatResponse);
        }

        return result;
    }

    @Override
    public List<PlayerStatResponse> getTeamPitchingStats(Long teamId, List<String> stats) {
        List<PlayerStatResponse> result = new ArrayList<>();

        for (String stat : stats) {
            List<PlayerStat> resultList = playerRepository.getTopPitcherByTeam(teamId, stat, 5);

            PlayerStatResponse playerStatResponse = new PlayerStatResponse(stat, resultList);

            result.add(playerStatResponse);
        }

        return result;
    }

    @Override
    public League getLeagueByTeam(Team team) {
        return teamRepository.findLeagueByTeam(team);
    }

    @Override
    public void readyToStartLeague(Long teamId) {
        Team team = getTeamById(teamId);

        team.setTeamStatus(TeamStatus.READY);

        teamRepository.save(team);
    }

}
