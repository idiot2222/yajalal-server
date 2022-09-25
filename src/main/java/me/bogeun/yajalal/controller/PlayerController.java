package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerInfoDto;
import me.bogeun.yajalal.payload.player.PlayerResponse;
import me.bogeun.yajalal.payload.player.PlayerUpdateDto;
import me.bogeun.yajalal.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;


    @PostMapping("/create/{userId}")
    public ResponseEntity<PlayerResponse> createPlayer(@PathVariable Long userId,
                                                       @RequestBody PlayerCreateDto createDto,
                                                       Errors errors) {
        try {
            if(errors.hasErrors()) {
                throw new IllegalArgumentException("error");
            }

            playerService.createNewPlayer(userId, createDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new PlayerResponse(e.getMessage()));
        }

        return ResponseEntity
                .ok()
                .body(new PlayerResponse("ok"));
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<PlayerResponse> getInfoByAccount(@PathVariable Long userId) {
        PlayerInfoDto playerInfo;

        try {
            playerInfo = playerService.getPlayerInfoByUserId(userId);
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new PlayerResponse(e.getMessage()));
        }

        return ResponseEntity
                .ok()
                .body(new PlayerResponse(playerInfo, "ok"));
    }

    @GetMapping("/allInfo/{userId}")
    public ResponseEntity<PlayerResponse> getAllInfoByAccount(@PathVariable Long userId) {
        PlayerInfoDto playerInfo;

        try {
            playerInfo = playerService.getPlayerAllInfoByUserId(userId);
        } catch (Exception e) {

            return ResponseEntity
                    .status(400)
                    .body(new PlayerResponse(e.getMessage()));
        }

        return ResponseEntity
                .ok()
                .body(new PlayerResponse(playerInfo, "ok"));
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<PlayerResponse> updatePlayerInfoByUserId(@PathVariable Long userId, PlayerUpdateDto updateDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity
                    .status(400)
                    .body(new PlayerResponse("error"));
        }

        playerService.updatePlayerInfo(userId, updateDto);

        return ResponseEntity
                .ok()
                .body(new PlayerResponse("ok"));
    }

}
