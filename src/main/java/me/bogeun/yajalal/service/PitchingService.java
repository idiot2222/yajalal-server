package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.player.Pitching;
import me.bogeun.yajalal.entity.player.Player;

public interface PitchingService {

    Pitching getPitchingByPlayer(Player player);

    void savePitching(Pitching pitching);
}
