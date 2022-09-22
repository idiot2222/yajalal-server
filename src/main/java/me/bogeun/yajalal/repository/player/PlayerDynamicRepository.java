package me.bogeun.yajalal.repository.player;

import me.bogeun.yajalal.entity.Position;

import java.util.List;

public interface PlayerDynamicRepository {

    List<Position> findAllSubPositionsByUserId(Long userId);
}
