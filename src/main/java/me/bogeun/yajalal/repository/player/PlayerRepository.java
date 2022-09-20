package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByName(String playerName);
}
