package me.bogeun.yajalal.payload.player;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.player.Position;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PlayerInfoDto {

    private String name;

    private Integer height;

    private Integer weight;

    private String description;

    private Position mainPosition;

    private final Set<Position> subPositions = new HashSet<>();
}
