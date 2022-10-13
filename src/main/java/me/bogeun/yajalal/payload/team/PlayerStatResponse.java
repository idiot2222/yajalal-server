package me.bogeun.yajalal.payload.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PlayerStatResponse {

    private String stat;

    private List<PlayerStat> stats;

}
