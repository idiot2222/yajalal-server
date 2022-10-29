package me.bogeun.yajalal.payload.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MatchResultDto {

    List<BattingRecordDto> batters;

    List<PitchingRecordDto> pitchers;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDate matchDate;

    Long myTeamId;

    Long yourTeamId;

}
