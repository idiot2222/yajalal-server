package me.bogeun.yajalal.payload.league;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.league.LeagueStatus;

@Getter
@Setter
@Builder
public class LeagueDto {

    private String name;

    private String description;

    private Integer limitOfTeam;

    private LeagueStatus leagueStatus;

    @Override
    public String toString() {
        return "LeagueDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", limitOfTeam=" + limitOfTeam +
                ", leagueStatus=" + leagueStatus +
                '}';
    }
}
