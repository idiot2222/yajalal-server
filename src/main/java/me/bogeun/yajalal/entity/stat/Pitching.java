package me.bogeun.yajalal.entity.stat;

import me.bogeun.yajalal.entity.Player;

import javax.persistence.*;

@Entity
public class Pitching {

    @Id
    @GeneratedValue
    private Long id;

    private Integer G;

    private Integer GS;

    private Integer W;

    private Integer L;

    private Integer H;

    private Integer SV;

    // ?? (?? ?? ??? ??)
    private Integer IP;

    // ??
    private Integer R;

    // ???
    private Integer ER;

    private Integer K;

    private Integer BB;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    Player player;
}
