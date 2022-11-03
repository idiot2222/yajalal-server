package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.match.MatchResultDto;
import me.bogeun.yajalal.payload.response.ResponseDto;
import me.bogeun.yajalal.service.match.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/create/{leagueId}")
    public ResponseEntity<ResponseDto> createNewMatch(@PathVariable Long leagueId,
                                                      @RequestBody MatchResultDto matchResultDto) {
        try {
            matchService.writeMatchResult(leagueId, matchResultDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new ResponseDto("error"));
        }

        return ResponseEntity.ok(new ResponseDto("ok"));
    }

}
