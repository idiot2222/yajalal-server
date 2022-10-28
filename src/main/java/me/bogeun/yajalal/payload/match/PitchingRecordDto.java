package me.bogeun.yajalal.payload.match;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.payload.player.PlayerIdDto;

@Getter
@Setter
public class PitchingRecordDto {

    private PlayerIdDto player;

    private Integer ip;
    private Integer k;
    private Integer bb;
    private Integer er;
    private PitchingDecision decision;

}
