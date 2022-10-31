package me.bogeun.yajalal.repository.team;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.repository.league.LeagueRepository;
import me.bogeun.yajalal.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamRepositoryImplBaboTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    LeagueRepository leagueRepository;

    @Test
    void findLeagueByTeam() {
        League league = new League();
        league.setName("testLeague");
        league.setDescription("test.");
        league.setLimitOfTeam(4);

        League savedLeague = leagueRepository.save(league);

        Team team = new Team();
        team.setName("testTeam");
        team.setDescription("test.");
        team.setLimitOfPlayer(9);
        team.setLeague(league);

        teamRepository.save(team);

        League foundLeague = teamRepository.findLeagueByTeam(team);

        assertEquals(savedLeague.getId(), foundLeague.getId());
    }

    @Test
    void findWinAndEarnedScore() {
        Team giants = teamService.getTeamById(3L);

        Integer winCount = teamRepository.findWinCount(giants);
        Integer loseCount = teamRepository.findLoseCount(giants);
        Integer drawCount = teamRepository.findDrawCount(giants);

        System.out.println(winCount);
        System.out.println(loseCount);
        System.out.println(drawCount);
    }
}