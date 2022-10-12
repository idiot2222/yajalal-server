package me.bogeun.yajalal.entity.stat;

import me.bogeun.yajalal.entity.Player;

import javax.persistence.*;

@Entity
public class Batting {

    @Id
    @GeneratedValue
    private Long id;

    private Integer G;

    // ??
    private Integer PA;

    // ??
    private Integer AB;

    private Integer H;

    private Integer H2;

    private Integer H3;

    private Integer HR;

    private Integer RBI;

    private Integer R;

    private Integer SO;

    private Integer BB;

    private Integer SB;

    private Integer CS;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    Player player;
}
