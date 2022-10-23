package me.bogeun.yajalal.payload.team;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamRecordDto {

    private String teamName;

    private int win;

    private int lose;

    private int draw;

    @Override
    public String toString() {
        return "TeamRecordDto{" +
                "teamName='" + teamName + '\'' +
                ", win=" + win +
                ", lose=" + lose +
                ", draw=" + draw +
                '}';
    }
}
