package me.bogeun.yajalal.payload.stat;

import lombok.Getter;
import me.bogeun.yajalal.entity.match.Batting;

import static me.bogeun.yajalal.util.Calculator.*;

@Getter
public class PersonalBattingStat {

    private String teamName;
    private int G;
    private String AVG;
    private String OBP;
    private String SLG;
    private int H;
    private int H2;
    private int H3;
    private int HR;
    private int BB;
    private int RBI;
    private int R;
    private int SB;
    private String SBP;
    private int SO;

    public static PersonalBattingStat convertToStat(String teamName, Batting batting) {
        PersonalBattingStat stat = new PersonalBattingStat();

        stat.teamName = teamName;
        stat.G = batting.getG();
        stat.H = batting.getH();
        stat.H2 = batting.getH2();
        stat.H3 = batting.getH3();
        stat.HR = batting.getHR();
        stat.RBI = batting.getRBI();
        stat.R = batting.getR();
        stat.BB = batting.getBB();
        stat.SB = batting.getSB();
        stat.SO = batting.getSO();
        stat.SBP = calSBP(batting.getSB(), batting.getSB() + batting.getCS());
        stat.AVG = batting.getAVG();
        stat.OBP = batting.getOBP();
        stat.SLG = batting.getSLG();

        return stat;
    }
}
