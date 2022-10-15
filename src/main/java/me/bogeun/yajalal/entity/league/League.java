package me.bogeun.yajalal.entity.league;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class League {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "league_name", unique = true, length = 20, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    private Integer limitOfTeam;

    @Enumerated(EnumType.STRING)
    private LeagueStatus leagueStatus;
}
