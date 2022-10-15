package me.bogeun.yajalal.repository.league;

import me.bogeun.yajalal.entity.league.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {
}
