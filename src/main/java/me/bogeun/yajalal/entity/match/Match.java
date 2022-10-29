package me.bogeun.yajalal.entity.match;

import lombok.Builder;
import lombok.NoArgsConstructor;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.Team;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Team winningTeam;

    @OneToOne(fetch = FetchType.LAZY)
    private Team losingTeam;

    private int winScore;

    private int loseScore;

    private LocalDate matchDate;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private League league;

    @Builder
    public Match(Team winningTeam, Team losingTeam, int winScore, int loseScore, LocalDate matchDate, League league) {
        this.winningTeam = winningTeam;
        this.losingTeam = losingTeam;
        this.winScore = winScore;
        this.loseScore = loseScore;
        this.matchDate = matchDate;
        this.league = league;
    }
}
