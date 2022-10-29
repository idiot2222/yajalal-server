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

    private Integer ER;

    private Integer K;

    private Integer BB;

    @Column(length = 5)
    private String ERA;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    Player player;

    @Builder
    public Pitching(Integer g, Integer GS, Integer w, Integer l, Integer h, Integer SV, Integer IP, Integer ER, Integer k, Integer BB) {
        G = g;
        this.GS = GS;
        W = w;
        L = l;
        H = h;
        this.SV = SV;
        this.IP = IP;
        this.ER = ER;
        K = k;
        this.BB = BB;
    }
}
