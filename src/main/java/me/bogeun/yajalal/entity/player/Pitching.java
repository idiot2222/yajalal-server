package me.bogeun.yajalal.entity.player;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@NoArgsConstructor
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

    private Integer IP;

    private Integer R;

    private Integer ER;

    private Integer K;

    private Integer BB;

    private Integer ERA;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    Player player;

    @Builder
    public Pitching(Integer g, Integer GS, Integer w, Integer l, Integer h, Integer SV, Integer IP, Integer r, Integer ER, Integer k, Integer BB) {
        G = g;
        this.GS = GS;
        W = w;
        L = l;
        H = h;
        this.SV = SV;
        this.IP = IP;
        R = r;
        this.ER = ER;
        K = k;
        this.BB = BB;
    }
}
