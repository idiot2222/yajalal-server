package me.bogeun.yajalal.repository.match;

import me.bogeun.yajalal.entity.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
