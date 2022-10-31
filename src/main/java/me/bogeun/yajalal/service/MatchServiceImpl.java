package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.match.Batting;
import me.bogeun.yajalal.entity.match.Match;
import me.bogeun.yajalal.entity.match.Pitching;
import me.bogeun.yajalal.entity.player.Player;
import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.payload.match.BattingRecordDto;
import me.bogeun.yajalal.payload.match.MatchResultDto;
import me.bogeun.yajalal.payload.match.PitchingRecordDto;
import me.bogeun.yajalal.repository.match.MatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static me.bogeun.yajalal.util.Calculator.*;

@RequiredArgsConstructor
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final TeamService teamService;
    private final LeagueService leagueService;
    private final PlayerService playerService;

    private final BattingService battingService;
    private final PitchingService pitchingService;

    @Transactional
    @Override
    public void writeMatchResult(Long leagueId, MatchResultDto matchResultDto) {
        List<BattingRecordDto> batters = matchResultDto.getBatters();
        List<PitchingRecordDto> pitchers = matchResultDto.getPitchers();
        League league = leagueService.getLeagueById(leagueId);
        Team myTeam = teamService.getTeamById(matchResultDto.getMyTeamId());
        Team yourTeam = teamService.getTeamById(matchResultDto.getYourTeamId());

        int rSum = getTotalR(matchResultDto.getBatters());
        int erSum = getTotalEr(matchResultDto.getPitchers());

        saveNewMatch(league, myTeam, yourTeam, rSum, erSum, matchResultDto.getMatchDate());

        saveBatterStats(batters);
        savePitcherStats(pitchers);
    }

    private void savePitcherStats(List<PitchingRecordDto> pitchers) {
        for (PitchingRecordDto pitcher : pitchers) {
            Long playerId = pitcher.getPlayer().getId();
            Player player = playerService.getPlayerById(playerId);

            Pitching pitching = pitchingService.getPitchingByPlayer(player);

            pitching.setG(pitching.getG() + 1);
            pitching.setGS(pitching.getGS() + pitcher.getSeq() == 1 ? 1 : 0);
            pitching.setIP(pitching.getIP() + pitcher.getIp());
            pitching.setER(pitching.getER() + pitcher.getEr());
            pitching.setK(pitching.getK() + pitcher.getK());
            pitching.setBB(pitching.getBB() + pitcher.getBb());
            pitching.setERA(calEra(pitching.getIP(), pitching.getER()));
            pitching.setDecision(pitcher.getDecision());

            pitchingService.savePitching(pitching);
        }
    }


    private String calEra(Integer ip, Integer er) {
        double v = (double) er * 27 / ip;

        return String.format("%.3f", v);
    }

    private void saveBatterStats(List<BattingRecordDto> batters) {
        for (BattingRecordDto batter : batters) {
            Long playerId = batter.getPlayer().getId();
            Player player = playerService.getPlayerById(playerId);

            Batting batting = battingService.getBattingByPlayer(player);

            batting.setG(batting.getG() + 1);
            batting.setPA(batting.getPA() + batter.getAb() + batter.getBb());
            batting.setAB(batting.getAB() + batter.getAb());
            batting.setH(batting.getH() + batter.getH() + batter.getH2() + batter.getH3() + batter.getHr());
            batting.setH2(batting.getH2() + batter.getH2());
            batting.setH3(batting.getH2() + batter.getH3());
            batting.setHR(batting.getHR() + batter.getHr());
            batting.setRBI(batting.getRBI() + batter.getRbi());
            batting.setR(batting.getR() + batter.getR());
            batting.setSO(batting.getSO() + batter.getK());
            batting.setBB(batting.getBB() + batter.getBb());
            batting.setSB(batting.getSB() + batter.getSb());
            batting.setCS(batting.getSB() + batter.getSb());
            batting.setAVG(calAVG(batting.getH(), batting.getAB()));
            batting.setOBP(calOBP(batting.getH(), batting.getBB(), batting.getPA()));
            batting.setSLG(calSLG(batting.getH(), batting.getH2(), batting.getH3(), batting.getHR(), batting.getAB()));

            battingService.saveBatting(batting);
        }
    }

    private void saveNewMatch(League league, Team myTeam, Team yourTeam, int rSum, int erSum, LocalDate matchDate) {
        Team winningTeam = myTeam;
        Team losingTeam = yourTeam;
        int winScore = rSum;
        int loseScore = erSum;

        if(rSum < erSum) {
            winningTeam = yourTeam;
            losingTeam = myTeam;
            winScore = erSum;
            loseScore = rSum;
        }

        Optional<Match> duplicateMatch = matchRepository.findMatch(winningTeam, losingTeam, matchDate);
        if(duplicateMatch.isPresent()) {
            return;
        }

        Match match = Match.builder()
                .league(league)
                .winningTeam(winningTeam)
                .losingTeam(losingTeam)
                .matchDate(matchDate)
                .winScore(winScore)
                .loseScore(loseScore)
                .build();

        matchRepository.save(match);
    }

    private int getTotalEr(List<PitchingRecordDto> pitchers) {
        int sum = 0;

        for (PitchingRecordDto pitcher : pitchers) {
            sum += pitcher.getEr();
        }

        return sum;
    }

    private int getTotalR(List<BattingRecordDto> batters) {
        int sum = 0;

        for (BattingRecordDto batter : batters) {
            sum += batter.getR();
        }

        return sum;
    }
}
