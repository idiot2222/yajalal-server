package me.bogeun.yajalal.payload.player;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.Position;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlayerInfoDto {

    private String name;

    private Integer length;

    private Integer weight;

    private String description;

    private Position mainPosition;

    private final List<Position> subPositions = new ArrayList<>();
}
