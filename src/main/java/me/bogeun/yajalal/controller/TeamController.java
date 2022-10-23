package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.league.Team;
import me.bogeun.yajalal.payload.response.ResponseDto;
import me.bogeun.yajalal.payload.stat.StatResponseDto;
import me.bogeun.yajalal.payload.team.TeamCreateDto;
import me.bogeun.yajalal.service.LeagueService;
import me.bogeun.yajalal.service.PlayerService;
import me.bogeun.yajalal.service.TeamService;
import me.bogeun.yajalal.validator.team.TeamCreateValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final LeagueService leagueService;

    private final TeamCreateValidator teamCreateValidator;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createNewTeam(@RequestBody TeamCreateDto createDto, Errors errors) {
        try {
            teamCreateValidator.validate(createDto, errors);
            if (errors.hasErrors()) {
                throw new IllegalArgumentException("error");
            }

            teamService.createNewTeam(createDto);

        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new ResponseDto(e.getMessage()));
        }

        return ResponseEntity
                .ok(new ResponseDto("ok"));
    }

    @GetMapping("/teamName/{playerId}")
    public ResponseEntity<String> getTeamNameByPlayerId(@PathVariable Long playerId) {
        Team team = playerService.getTeamByPlayerId(playerId);

        return ResponseEntity
                .ok(team.getName());
    }

    @GetMapping("/dashboard/batting/{playerId}")
    public ResponseEntity<ResponseDto> getTeamBattingDashboardByPlayerId(@PathVariable Long playerId) {
        Long teamId = playerService.getTeamByPlayerId(playerId).getId();
        List<StatResponseDto> teamBattingStats = teamService.getTeamBattingStats(teamId, List.of("H", "RBI", "R", "HR"));

        return ResponseEntity
                .ok(new ResponseDto(teamBattingStats, "ok"));
    }

    @GetMapping("/dashboard/pitching/{playerId}")
    public ResponseEntity<ResponseDto> getTeamPitchingDashboardByPlayerId(@PathVariable Long playerId) {
        Long teamId = playerService.getTeamByPlayerId(playerId).getId();
        List<StatResponseDto> teamPitchingStats = teamService.getTeamPitchingStats(teamId, List.of("W", "H", "SV", "K"));

        return ResponseEntity
                .ok(new ResponseDto(teamPitchingStats, "ok"));
    }

    @PostMapping("/ready/{teamId}")
    public ResponseEntity<String> readyToStartLeague(@PathVariable Long teamId) {
        teamService.readyToStartLeague(teamId);
        leagueService.checkTeamsStatusInLeague(teamId);

        return ResponseEntity.ok("ok");
    }

}
