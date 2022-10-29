package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.match.Batting;
import me.bogeun.yajalal.entity.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BattingRepository extends JpaRepository<Batting, Long> {

    Optional<Batting> findByPlayer(Player player);

}
