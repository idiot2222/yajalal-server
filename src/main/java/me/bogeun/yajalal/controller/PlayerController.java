package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.entity.player.Player;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerIdDto;
import me.bogeun.yajalal.payload.player.PlayerInfoDto;
import me.bogeun.yajalal.payload.player.PlayerUpdateDto;
import me.bogeun.yajalal.payload.stat.StatRequestDto;
import me.bogeun.yajalal.payload.response.ResponseDto;
import me.bogeun.yajalal.payload.stat.StatResponseDto;
import me.bogeun.yajalal.service.LeagueService;
import me.bogeun.yajalal.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final LeagueService leagueService;


    @PostMapping("/create/{userId}")
    public ResponseEntity<ResponseDto> createPlayer(@PathVariable Long userId,
                                                    @Valid @RequestBody PlayerCreateDto createDto,
                                                    Errors errors) {
        try {
            if (errors.hasErrors()) {
                throw new IllegalArgumentException("error");
            }

            playerService.createNewPlayer(userId, createDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new ResponseDto(e.getMessage()));
        }

        return ResponseEntity
                .ok()
                .body(new ResponseDto("ok"));
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<ResponseDto> getInfoByAccount(@PathVariable Long userId) {
        PlayerInfoDto playerInfo;

        try {
            playerInfo = playerService.getPlayerInfoByUserId(userId);
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new ResponseDto(e.getMessage()));
        }

        return ResponseEntity
                .ok()
                .body(new ResponseDto(playerInfo, "ok"));
    }

    @GetMapping("/allInfo/{userId}")
    public ResponseEntity<ResponseDto> getAllInfoByAccount(@PathVariable Long userId) {
        PlayerInfoDto playerInfo;

        try {
            playerInfo = playerService.getPlayerAllInfoByUserId(userId);
        } catch (Exception e) {

            return ResponseEntity
                    .status(400)
                    .body(new ResponseDto(e.getMessage()));
        }

        return ResponseEntity
                .ok()
                .body(new ResponseDto(playerInfo, "ok"));
    }

    @GetMapping("/playerList/{teamId}")
    public ResponseEntity<ResponseDto> getPlayerListByTeam(@PathVariable Long teamId) {
        List<PlayerIdDto> playerList = playerService.getPlayerAllByTeamId(teamId);

        return ResponseEntity
                .ok()
                .body(new ResponseDto(playerList, "ok"));
    }

    @GetMapping("/teamId/{playerId}")
    public ResponseEntity<Long> getTeamIdByPlayerId(@PathVariable Long playerId) {
        Team team = playerService.getTeamByPlayerId(playerId);

        return ResponseEntity
                .ok()
                .body(team.getId());
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<ResponseDto> updatePlayerInfoByUserId(@PathVariable Long userId, @Valid @RequestBody PlayerUpdateDto updateDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity
                    .status(400)
                    .body(new ResponseDto("error"));
        }

        playerService.updatePlayerInfo(userId, updateDto);

        return ResponseEntity
                .ok()
                .body(new ResponseDto("ok"));
    }

    @GetMapping("/playerId/{userId}")
    public ResponseEntity<Long> getPlayerId(@PathVariable Long userId) {
        Player player = playerService.getPlayerByUserId(userId);

        return ResponseEntity
                .ok(player.getId());
    }

    @PostMapping("/ranking/batting/{playerId}")
    public ResponseEntity<ResponseDto> getTopBatters(
            @PathVariable Long playerId,
            @RequestBody StatRequestDto requestDto) {

        League league = playerService.getLeagueByPlayerId(playerId);
        List<StatResponseDto> result = playerService.getTopBattersByLeague(league, requestDto.getCount(), requestDto.getStats());

        return ResponseEntity
                .ok()
                .body(new ResponseDto(result, "ok"));
    }

    @PostMapping("/ranking/pitching/{playerId}")
    public ResponseEntity<ResponseDto> getTopPitchers(
            @PathVariable Long playerId,
            @RequestBody StatRequestDto requestDto) {

        League league = playerService.getLeagueByPlayerId(playerId);
        List<StatResponseDto> result = playerService.getTopPitchersByLeague(league, requestDto.getCount(), requestDto.getStats());

        return ResponseEntity
                .ok()
                .body(new ResponseDto(result, "ok"));
    }

}
