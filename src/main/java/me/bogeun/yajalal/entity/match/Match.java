package me.bogeun.yajalal.entity.match;

import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.Team;

import javax.persistence.*;

@Entity
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Team winningTeam;

    @OneToOne(fetch = FetchType.LAZY)
    private Team losingTeam;

    private int winScore;

    private int loseScore;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private League league;

}
