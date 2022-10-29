package me.bogeun.yajalal.service;

import me.bogeun.yajalal.payload.match.MatchResultDto;

public interface MatchService {
    void writeMatchResult(Long leagueId, MatchResultDto matchResultDto);
}
