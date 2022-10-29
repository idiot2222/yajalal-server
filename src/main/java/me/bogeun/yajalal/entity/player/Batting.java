package me.bogeun.yajalal.entity.player;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Batting {

    @Id
    @GeneratedValue
    private Long id;

    private Integer G;

    private Integer PA;

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

    private Integer AVG;

    private Integer OBP;

    private Integer SLG;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    Player player;


    @Builder
    public Batting(Integer g, Integer PA, Integer AB, Integer h, Integer h2, Integer h3, Integer HR, Integer RBI, Integer r, Integer SO, Integer BB, Integer SB, Integer CS, Integer OBP, Integer SLG, Player player) {
        G = g;
        this.PA = PA;
        this.AB = AB;
        H = h;
        H2 = h2;
        H3 = h3;
        this.HR = HR;
        this.RBI = RBI;
        R = r;
        this.SO = SO;
        this.BB = BB;
        this.SB = SB;
        this.CS = CS;
        this.OBP = OBP;
        this.SLG = SLG;
        this.player = player;
    }
}
