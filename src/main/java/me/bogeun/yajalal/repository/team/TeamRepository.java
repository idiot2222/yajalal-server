package me.bogeun.yajalal.repository.team;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamDynamicRepository {

    Optional<Team> findByName(String name);

    boolean existsByName(String name);

    List<Team> findAllByLeague(League league);
}
