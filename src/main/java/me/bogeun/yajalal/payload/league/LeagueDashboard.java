package me.bogeun.yajalal.payload.league;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.bogeun.yajalal.payload.team.TeamRecordDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LeagueDashboard {

    LeagueDto leagueInfo;

    List<TeamRecordDto> teamRecords;

}
