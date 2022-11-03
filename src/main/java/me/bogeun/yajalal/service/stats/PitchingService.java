package me.bogeun.yajalal.service.stats;

import me.bogeun.yajalal.entity.match.Pitching;
import me.bogeun.yajalal.entity.player.Player;

public interface PitchingService {

    Pitching getPitchingByPlayer(Player player);

    void savePitching(Pitching pitching);
}
