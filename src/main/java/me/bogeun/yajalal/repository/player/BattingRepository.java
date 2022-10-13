package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.stat.Batting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattingRepository extends JpaRepository<Batting, Long> {
}
