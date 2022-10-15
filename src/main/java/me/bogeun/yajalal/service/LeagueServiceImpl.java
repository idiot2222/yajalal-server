package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.LeagueStatus;
import me.bogeun.yajalal.entity.league.Team;
import me.bogeun.yajalal.entity.league.TeamStatus;
import me.bogeun.yajalal.mapper.LeagueMapper;
import me.bogeun.yajalal.payload.league.LeagueCreateDto;
import me.bogeun.yajalal.payload.league.LeagueUpdateDto;
import me.bogeun.yajalal.repository.league.LeagueRepository;
import me.bogeun.yajalal.repository.team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final TeamService teamService;
    private final TeamRepository teamRepository;

    private final LeagueMapper leagueMapper;

    @Override
    public League createNewLeague(LeagueCreateDto createDto) {
        League league = leagueMapper.createDtoToEntity(createDto);
        league.setLeagueStatus(LeagueStatus.WAITING);

        return leagueRepository.save(league);
    }

    @Override
    public League getLeagueById(Long leagueId) {
        return leagueRepository.findById(leagueId).orElseThrow(() -> new IllegalArgumentException("invalid league id."));
    }

    @Override
    public List<Team> getTeamListByLeague(League league) {
        return teamRepository.findTeamListByLeague(league);
    }

    @Override
    public void updateLeagueInfo(Long leagueId, LeagueUpdateDto updateDto) {
        League league = getLeagueById(leagueId);

        league.setName(updateDto.getName());
        league.setDescription(updateDto.getDescription());
        league.setLimitOfTeam(updateDto.getLimitOfTeam());

        leagueRepository.save(league);
    }

    @Override
    public void joinTeam(Long leagueId, Long teamId) {
        League league = getLeagueById(leagueId);
        Team team = teamService.getTeamById(teamId);

        if(team.getTeamStatus() != TeamStatus.NOT_JOINING) {
            throw new IllegalArgumentException("this team is already participated league.");
        }

        team.setLeague(league);
        team.setTeamStatus(TeamStatus.JOINING);

        teamRepository.save(team);
    }

    // ?? ?? ??? ??? ??? ???? ?? ?? ? ??
    @Override
    public void leaveTeam(Long leagueId, Long teamId) {
        Team team = teamService.getTeamById(teamId);

        team.setLeague(null);
        team.setTeamStatus(TeamStatus.NOT_JOINING);

        teamRepository.save(team);
    }

    @Override
    public void startLeague(Long leagueId) {
        League league = getLeagueById(leagueId);
        LeagueStatus leagueStatus = league.getLeagueStatus();

        if (leagueStatus == LeagueStatus.READY) {


            league.setLeagueStatus(LeagueStatus.PROCEEDING);
        }
        else {
            throw new IllegalArgumentException("league status is not ready.");
        }
    }

    @Override
    public void endLeague(Long leagueId) {
        League league = getLeagueById(leagueId);
        LeagueStatus leagueStatus = league.getLeagueStatus();

        if (leagueStatus == LeagueStatus.PROCEEDING) {
            league.setLeagueStatus(LeagueStatus.FINISHED);
        }
        else {
            throw new IllegalArgumentException("league status is not proceeding.");
        }
    }

    @Override
    public void checkTeamsStatusInLeague(Long teamId) {
        Team team = teamService.getTeamById(teamId);
        League league = teamService.getLeagueByTeam(team);

        List<Team> teamList = getTeamListByLeague(league);

        for (Team t : teamList) {
            if(t.getTeamStatus() != TeamStatus.READY) {
                return;
            }
        }
        league.setLeagueStatus(LeagueStatus.READY);

        leagueRepository.save(league);
    }
}
