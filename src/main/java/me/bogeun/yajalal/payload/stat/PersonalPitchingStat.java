package me.bogeun.yajalal.payload.stat;

import lombok.Getter;
import me.bogeun.yajalal.entity.match.Pitching;

@Getter
public class PersonalPitchingStat {

    private String teamName;
    private int G;
    private int GS;
    private String ERA;
    private int W;
    private int L;
    private int H;
    private int SV;
    private int K;
    private int BB;
    private int IP;

    public static PersonalPitchingStat convertToStat(String teamName, Pitching pitching) {
        PersonalPitchingStat stat = new PersonalPitchingStat();

        stat.teamName = teamName;
        stat.G = pitching.getG();
        stat.GS = pitching.getGS();
        stat.ERA = pitching.getERA();
        stat.W = pitching.getW();
        stat.L = pitching.getL();
        stat.H = pitching.getH();
        stat.SV = pitching.getSV();
        stat.K = pitching.getK();
        stat.BB = pitching.getBB();
        stat.IP = pitching.getIP();

        return stat;
    }
}
