package me.bogeun.yajalal.payload.match;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.payload.player.PlayerIdDto;

@Getter
@Setter
public class PitchingRecordDto {

    private PlayerIdDto player;

    private int seq;
    private int ip;
    private int k;
    private int bb;
    private int er;
    private PitchingDecision decision;

    @Override
    public String toString() {
        return "PitchingRecordDto{" +
                "player=" + player.getName() +
                ", ip=" + ip +
                ", k=" + k +
                ", bb=" + bb +
                ", er=" + er +
                ", decision=" + decision +
                '}';
    }
}
