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

    @Column(length = 5)
    private String AVG;

    @Column(length = 5)
    private String OBP;

    @Column(length = 5)
    private String SLG;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    Player player;

}
