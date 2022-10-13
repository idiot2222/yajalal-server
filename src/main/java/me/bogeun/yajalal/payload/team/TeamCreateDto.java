package me.bogeun.yajalal.payload.team;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamCreateDto {

    private String name;

    private String description;

    private Integer limitOfPlayer;

}
