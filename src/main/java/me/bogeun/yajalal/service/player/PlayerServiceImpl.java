package me.bogeun.yajalal.service.player;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.account.Account;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.match.Batting;
import me.bogeun.yajalal.entity.match.Pitching;
import me.bogeun.yajalal.entity.player.Player;
import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.mapper.PlayerMapper;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerIdDto;
import me.bogeun.yajalal.payload.player.PlayerInfoDto;
import me.bogeun.yajalal.payload.player.PlayerUpdateDto;
import me.bogeun.yajalal.payload.stat.PersonalBattingStat;
import me.bogeun.yajalal.payload.stat.PersonalPitchingStat;
import me.bogeun.yajalal.payload.stat.PlayerStat;
import me.bogeun.yajalal.payload.stat.StatResponseDto;
import me.bogeun.yajalal.repository.player.PlayerRepository;
import me.bogeun.yajalal.service.account.AccountService;
import me.bogeun.yajalal.service.stats.BattingService;
import me.bogeun.yajalal.service.stats.PitchingService;
import me.bogeun.yajalal.service.team.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private final AccountService accountService;
    private final BattingService battingService;
    private final PitchingService pitchingService;

    private final PlayerMapper playerMapper;



    @Override
    public Player createNewPlayer(Long userId, PlayerCreateDto createDto) {
        Player newPlayer = playerMapper.createDtoToEntity(createDto);
        Account account = accountService.getAccountById(userId);
        newPlayer.setAccount(account);
        newPlayer.getSubPositions().addAll(createDto.getSubPositions());

        return playerRepository.save(newPlayer);
    }

    @Override
    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId).orElseThrow(() -> new IllegalArgumentException("invalid player id."));
    }

    @Override
    public Player getPlayerByUserId(Long userId) {
        Account account = accountService.getAccountById(userId);

        return playerRepository.findByAccount(account).orElseThrow(() -> new IllegalArgumentException("player not created."));
    }

    @Override
    public PlayerInfoDto getPlayerInfoById(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new IllegalArgumentException("invalid player id."));

        return playerMapper.entityToInfoDto(player);
    }

    @Override
    public PlayerInfoDto getPlayerInfoByUserId(Long userId) {
        Account account = accountService.getAccountById(userId);
        Player player = playerRepository.findByAccount(account).orElseThrow(() -> new IllegalArgumentException("player not created."));


        return playerMapper.entityToInfoDto(player);
    }

    @Override
    @Transactional
    public PlayerInfoDto getPlayerAllInfoByUserId(Long userId) {
        Account account = accountService.getAccountById(userId);
        Player player = playerRepository.findByAccount(account).orElseThrow(() -> new IllegalArgumentException("player not created."));

        PlayerInfoDto infoDto = playerMapper.entityToInfoDto(player);
        infoDto.getSubPositions().addAll(player.getSubPositions());

        return infoDto;
    }

    @Override
    public Player getPlayerByName(String playerName) {
        return playerRepository.findByName(playerName).orElseThrow(() -> new IllegalArgumentException("invalid player name."));
    }

    @Override
    public Player updatePlayerInfo(Long userId, PlayerUpdateDto updateDto) {
        Player player = getPlayerByUserId(userId);
        player.setName(updateDto.getName());
        player.setHeight(updateDto.getHeight());
        player.setWeight(updateDto.getWeight());
        player.setDescription(updateDto.getDescription());
        player.setMainPosition(updateDto.getMainPosition());
        player.setSubPositions(updateDto.getSubPositions());

        playerRepository.save(player);

        return player;
    }

    @Override
    public void joinTheClub(Long playerId, Long teamId) {
        Player player = getPlayerById(playerId);
        Team team = teamService.getTeamById(teamId);

        player.setTeam(team);

        playerRepository.save(player);
    }

    @Override
    public Team getTeamByPlayerId(Long playerId) {
        return playerRepository.getTeamByPlayerId(playerId);
    }

    @Override
    public League getLeagueByPlayerId(Long playerId) {
        Team team = getTeamByPlayerId(playerId);

        return teamService.getLeagueByTeam(team);
    }

    @Override
    @Transactional
    public List<StatResponseDto> getTopBattersByLeague(League league, int count, String[] stats) {
        List<StatResponseDto> list = new ArrayList<>();

        for (String stat : stats) {
            List<PlayerStat> batterStats = playerRepository.findTopBattersStatByLeague(league, stat, 5);

            list.add(new StatResponseDto(stat, batterStats));
        }

        return list;
    }

    @Override
    @Transactional
    public List<StatResponseDto> getTopPitchersByLeague(League league, int count, String[] stats) {
        List<StatResponseDto> list = new ArrayList<>();

        for (String stat : stats) {
            List<PlayerStat> pitcherStats = playerRepository.findTopPitchersStatByLeague(league, stat, 5);

            list.add(new StatResponseDto(stat, pitcherStats));
        }

        return list;
    }

    @Override
    public void setBackNumber(Long playerId, Integer backNumber) {
        Player player = getPlayerById(playerId);

        player.setBackNumber(backNumber);
    }

    @Override
    public List<PlayerIdDto> getPlayerAllByTeamId(Long teamId) {
        Team team = teamService.getTeamById(teamId);

        return playerRepository.findAllByTeam(team).stream()
                .map(x -> new PlayerIdDto(x.getId(), x.getName(), x.getBackNumber()))
                .collect(Collectors.toList());
    }

    @Override
    public PersonalBattingStat getPersonalBattingStats(Long playerId) {
        Player player = getPlayerById(playerId);
        Team team = getTeamByPlayerId(playerId);
        Batting batting = battingService.getBattingByPlayer(player);

        return PersonalBattingStat.convertToStat(team.getName(), batting);
    }

    @Override
    public PersonalPitchingStat getPersonalPitchingStats(Long playerId) {
        Player player = getPlayerById(playerId);
        Team team = getTeamByPlayerId(playerId);
        Pitching pitching = pitchingService.getPitchingByPlayer(player);

        return PersonalPitchingStat.convertToStat(team.getName(), pitching);
    }
}
