package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.account.Account;
import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.entity.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerDynamicRepository {

    Optional<Player> findByName(String playerName);

    Optional<Player> findByAccount(Account account);

    List<Player> findAllByTeam(Team team);

}
