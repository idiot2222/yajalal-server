package me.bogeun.yajalal.entity.match;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.bogeun.yajalal.entity.player.Player;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Batting {

    @Id
    @GeneratedValue
    private Long id;

    private int G;

    private int PA;

    private int AB;

    private int H;

    private int H2;

    private int H3;

    private int HR;

    private int RBI;

    private int R;

    private int SO;

    private int BB;

    private int SB;

    private int CS;

    @Column(length = 5)
    private String AVG;

    @Column(length = 5)
    private String OBP;

    @Column(length = 5)
    private String SLG;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    Player player;

    public Batting(Player player) {
        this.player = player;
    }
}
