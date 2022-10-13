package me.bogeun.yajalal.payload.team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamUpdateDto {

    private String name;

    private String description;

    private Integer limitOfPlayer;

}
