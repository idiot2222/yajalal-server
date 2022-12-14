package me.bogeun.yajalal.entity.team;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.league.League;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "team_name", unique = true, length = 20, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    private Integer limitOfPlayer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @Enumerated(EnumType.STRING)
    private TeamStatus teamStatus;

}
