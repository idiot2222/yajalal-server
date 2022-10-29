package me.bogeun.yajalal.repository.match;

import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.entity.match.Match;

import java.time.LocalDate;
import java.util.Optional;

public interface MatchDynamicRepository {
    Optional<Match> findMatch(Team winningTeam, Team losingTeam, LocalDate matchDate);
}
