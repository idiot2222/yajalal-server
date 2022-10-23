package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.payload.league.LeagueDashboard;
import me.bogeun.yajalal.payload.league.LeagueDto;
import me.bogeun.yajalal.service.LeagueService;
import me.bogeun.yajalal.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;
    private final PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<String> createNewLeague(@RequestBody LeagueDto createDto) {
        leagueService.createNewLeague(createDto);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/dashboard/{playerId}")
    public ResponseEntity<LeagueDashboard> getLeagueDashboardByPlayerId(@PathVariable Long playerId) {
        League league = playerService.getLeagueByPlayerId(playerId);
        LeagueDashboard dashboard = leagueService.getLeagueDashboardById(league.getId());

        return ResponseEntity
                .ok()
                .body(dashboard);
    }

    @PostMapping("/update/{leagueId}")
    public ResponseEntity<String> updateLeague(@PathVariable Long leagueId,
                                               @RequestBody LeagueDto leagueDto) {
        leagueService.updateLeagueInfo(leagueId, leagueDto);

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/join/{leagueId}/{teamId}")
    public ResponseEntity<String> joinTeam(@PathVariable Long leagueId,
                                             @PathVariable Long teamId) {
        leagueService.joinTeam(leagueId, teamId);

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/leave/{leagueId}/{teamId}")
    public ResponseEntity<String> leaveTeam(@PathVariable Long leagueId,
                                              @PathVariable Long teamId) {
        leagueService.leaveTeam(leagueId, teamId);

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/start/{leagueId}")
    public ResponseEntity<String> startLeague(@PathVariable Long leagueId) {
        try {
            leagueService.startLeague(leagueId);
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body("league is not ready yet.");
        }

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/end/{leagueId}")
    public ResponseEntity<String> endLeague(@PathVariable Long leagueId) {
        try {
            leagueService.endLeague(leagueId);
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body("league is not proceeding.");
        }

        return ResponseEntity.ok("ok");
    }

}
