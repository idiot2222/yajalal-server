package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.player.Batting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattingRepository extends JpaRepository<Batting, Long> {
}
