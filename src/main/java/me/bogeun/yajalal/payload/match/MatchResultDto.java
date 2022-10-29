package me.bogeun.yajalal.payload.match;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MatchResultDto {

    List<BattingRecordDto> batters;

    List<PitchingRecordDto> pitchers;

    LocalDate matchDate;

}
