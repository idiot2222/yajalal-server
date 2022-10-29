package me.bogeun.yajalal.payload.match;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.payload.player.PlayerIdDto;

@Getter
@Setter
public class BattingRecordDto {

    private PlayerIdDto player;

    private int ab;
    private int h;
    private int h2;
    private int h3;
    private int hr;
    private int bb;
    private int rbi;
    private int r;
    private int sb;
    private int cs;
    private int k;

    @Override
    public String toString() {
        return "BattingRecordDto{" +
                "player=" + player.getName() +
                ", ab=" + ab +
                ", h=" + h +
                ", h2=" + h2 +
                ", h3=" + h3 +
                ", hr=" + hr +
                ", bb=" + bb +
                ", rbi=" + rbi +
                ", r=" + r +
                ", sb=" + sb +
                ", cs=" + cs +
                ", k=" + k +
                '}';
    }
}
