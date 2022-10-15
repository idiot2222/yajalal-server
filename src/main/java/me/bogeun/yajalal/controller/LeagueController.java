package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.league.LeagueCreateDto;
import me.bogeun.yajalal.payload.league.LeagueUpdateDto;
import me.bogeun.yajalal.service.LeagueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping("/create")
    public ResponseEntity<String> createNewLeague(@RequestBody LeagueCreateDto createDto) {
        leagueService.createNewLeague(createDto);

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/update/{leagueId}")
    public ResponseEntity<String> updateLeague(@PathVariable Long leagueId,
                                               @RequestBody LeagueUpdateDto leagueUpdateDto) {
        leagueService.updateLeagueInfo(leagueId, leagueUpdateDto);

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
