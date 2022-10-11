package me.bogeun.yajalal.repository.team;

import me.bogeun.yajalal.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
