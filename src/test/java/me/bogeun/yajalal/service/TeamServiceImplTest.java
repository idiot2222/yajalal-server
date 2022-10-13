package me.bogeun.yajalal.service;

import me.bogeun.yajalal.payload.team.PlayerStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeamServiceImplTest {

    @Autowired
    TeamService teamService;

    @Test
    void getTeamBattingStats() {
        List<String> stats = List.of("H", "HR");
        TeamDashboard teamBattingStats = teamService.getTeamBattingStats(3L, stats);

        for (String stat : stats) {
            List<PlayerStat> playerStats = teamBattingStats.get(stat);

            for (PlayerStat playerStat : playerStats) {
                System.out.println(playerStat);
            }
        }
    }

    @Test
    void getTeamPitchingStats() {
        List<String> stats = List.of("K", "W");
        TeamDashboard teamPitchingStats = teamService.getTeamPitchingStats(3L, stats);

        for (String stat : stats) {
            List<PlayerStat> playerStats = teamPitchingStats.get(stat);

            for (PlayerStat playerStat : playerStats) {
                System.out.println(playerStat);
            }
        }
    }
}