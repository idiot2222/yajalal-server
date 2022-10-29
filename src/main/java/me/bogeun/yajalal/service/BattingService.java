package me.bogeun.yajalal.service;

import me.bogeun.yajalal.entity.match.Batting;
import me.bogeun.yajalal.entity.player.Player;

public interface BattingService {

    Batting getBattingByPlayer(Player player);

    void saveBatting(Batting batting);
}
