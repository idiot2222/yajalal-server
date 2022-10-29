package me.bogeun.yajalal.entity.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.bogeun.yajalal.entity.player.Player;
import me.bogeun.yajalal.payload.match.PitchingDecision;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Pitching {

    @Id
    @GeneratedValue
    private Long id;

    private int G;

    private int GS;

    private int W;

    private int L;

    private int H;

    private int SV;

    private int IP;

    private int ER;

    private int K;

    private int BB;

    @Column(length = 5)
    private String ERA;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    Player player;


    public void setDecision(PitchingDecision decision) {
        if(decision == PitchingDecision.W) {
            this.W++;
        }
        else if(decision == PitchingDecision.L) {
            this.L++;
        }
        else if(decision == PitchingDecision.H) {
            this.H++;
        }
        else if(decision == PitchingDecision.SV) {
            this.SV++;
        }
    }

    public Pitching(Player player) {
        this.player = player;
    }
}
