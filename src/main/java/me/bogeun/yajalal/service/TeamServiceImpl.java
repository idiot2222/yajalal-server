package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Team;
import me.bogeun.yajalal.mapper.TeamMapper;
import me.bogeun.yajalal.payload.team.TeamCreateDto;
import me.bogeun.yajalal.payload.team.TeamInfoDto;
import me.bogeun.yajalal.payload.team.TeamUpdateDto;
import me.bogeun.yajalal.repository.team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public Team createNewTeam(TeamCreateDto createDto) {
        Team team = teamMapper.createDtoToEntity(createDto);

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

        if(!name.isBlank()) {
            team.setName(name);
        }
        if(!description.isBlank()) {
            team.setDescription(description);
        }
        team.setLimitOfPlayer(limit);

        teamRepository.save(team);
    }
}
