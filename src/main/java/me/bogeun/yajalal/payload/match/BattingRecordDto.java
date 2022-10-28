package me.bogeun.yajalal.payload.match;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.payload.player.PlayerIdDto;

@Getter
@Setter
public class BattingRecordDto {

    private PlayerIdDto player;

    private Integer ab;
    private Integer h;
    private Integer h2;
    private Integer h3;
    private Integer hr;
    private Integer bb;
    private Integer rbi;
    private Integer r;
    private Integer sb;
    private Integer cs;
    private Integer k;

}
