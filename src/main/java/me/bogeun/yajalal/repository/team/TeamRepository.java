package me.bogeun.yajalal.repository.team;

import me.bogeun.yajalal.entity.league.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamDynamicRepository {

    Optional<Team> findByName(String name);

    boolean existsByName(String name);

}
