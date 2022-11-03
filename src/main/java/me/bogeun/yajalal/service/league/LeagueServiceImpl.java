package me.bogeun.yajalal.service.league;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.LeagueStatus;
import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.entity.team.TeamStatus;
import me.bogeun.yajalal.mapper.LeagueMapper;
import me.bogeun.yajalal.payload.league.LeagueDashboard;
import me.bogeun.yajalal.payload.league.LeagueDto;
import me.bogeun.yajalal.payload.team.TeamRecordDto;
import me.bogeun.yajalal.repository.league.LeagueRepository;
import me.bogeun.yajalal.repository.team.TeamRepository;
import me.bogeun.yajalal.service.team.TeamService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final TeamService teamService;
    private final TeamRepository teamRepository;

    private final LeagueMapper leagueMapper;

    @Override
    public League createNewLeague(LeagueDto createDto) {
        League league = leagueMapper.dtoToEntity(createDto);
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
    public void updateLeagueInfo(Long leagueId, LeagueDto leagueDto) {
        League league = getLeagueById(leagueId);

        league.setName(leagueDto.getName());
        league.setDescription(leagueDto.getDescription());
        league.setLimitOfTeam(leagueDto.getLimitOfTeam());

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

    @Override
    public LeagueDashboard getLeagueDashboardById(Long leagueId) {
        League league = getLeagueById(leagueId);
        List<Team> teamList = teamService.getTeamsByLeague(league);

        LeagueDto leagueDto = leagueMapper.entityToDto(league);
        List<TeamRecordDto> teamRecordList = convertRecordList(teamList);

        return new LeagueDashboard(leagueDto, teamRecordList);
    }

    private List<TeamRecordDto> convertRecordList(List<Team> teamList) {
        List<TeamRecordDto> list = new ArrayList<>();

        for (Team team : teamList) {
            Integer winCount = teamRepository.findWinCount(team);
            Integer loseCount = teamRepository.findLoseCount(team);
            Integer drawCount = teamRepository.findDrawCount(team);

            TeamRecordDto recordDto = TeamRecordDto.builder()
                    .teamName(team.getName())
                    .win(winCount)
                    .lose(loseCount)
                    .draw(drawCount)
                    .build();

            list.add(recordDto);
        }

        return list;
    }

}
