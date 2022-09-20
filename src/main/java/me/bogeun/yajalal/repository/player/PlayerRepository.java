package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
