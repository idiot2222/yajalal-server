package me.bogeun.yajalal.service.stats;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.match.Batting;
import me.bogeun.yajalal.entity.player.Player;
import me.bogeun.yajalal.repository.player.BattingRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BattingServiceImpl implements BattingService {

    private final BattingRepository battingRepository;

    @Override
    public Batting getBattingByPlayer(Player player) {
        return battingRepository.findByPlayer(player).orElse(new Batting(player));
    }

    @Override
    public void saveBatting(Batting batting) {
        battingRepository.save(batting);
    }
}
