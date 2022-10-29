package me.bogeun.yajalal.service;

import me.bogeun.yajalal.payload.league.LeagueDashboard;
import me.bogeun.yajalal.payload.team.TeamRecordDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LeagueServiceImplBaboTest {

    @Autowired
    LeagueService leagueService;

    @Test
    void getLeagueDashboardByPlayerId() {
        LeagueDashboard dashboard = leagueService.getLeagueDashboardById(227L);

        System.out.println(dashboard.getLeagueInfo());
        List<TeamRecordDto> teamRecords = dashboard.getTeamRecords();

        for (TeamRecordDto teamRecord : teamRecords) {
            System.out.println(teamRecord);
        }
    }
}