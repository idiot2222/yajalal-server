package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.player.Pitching;
import me.bogeun.yajalal.entity.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PitchingRepository extends JpaRepository<Pitching, Long> {

    Optional<Pitching> findByPlayer(Player player);

}
