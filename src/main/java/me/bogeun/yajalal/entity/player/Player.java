package me.bogeun.yajalal.entity.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.bogeun.yajalal.entity.account.Account;
import me.bogeun.yajalal.entity.league.Team;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "player_name", length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer weight;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position mainPosition;

    private Integer backNumber;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "sub_positions")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Position> subPositions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

}
