package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.player.Player;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerInfoDto;
import me.bogeun.yajalal.payload.player.PlayerUpdateDto;
import me.bogeun.yajalal.payload.response.ResponseDto;
import me.bogeun.yajalal.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;


    @PostMapping("/create/{userId}")
    public ResponseEntity<ResponseDto> createPlayer(@PathVariable Long userId,
                                                    @Valid @RequestBody PlayerCreateDto createDto,
                                                    Errors errors) {
        try {
            if(errors.hasErrors()) {
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
    public ResponseEntity<Long> getPlayerId(@PathVariable Long userId){
        Player player = playerService.getPlayerByUserId(userId);

        return ResponseEntity
                .ok(player.getId());
    }

}
