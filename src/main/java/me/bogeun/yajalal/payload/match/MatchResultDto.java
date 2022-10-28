package me.bogeun.yajalal.payload.match;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchResultDto {

    List<BattingRecordDto> batters;

    List<PitchingRecordDto> pitchers;

}
