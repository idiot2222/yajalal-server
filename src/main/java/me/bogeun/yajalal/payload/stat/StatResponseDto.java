package me.bogeun.yajalal.payload.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StatResponseDto {

    private String stat;

    private List<PlayerStat> stats;

}
