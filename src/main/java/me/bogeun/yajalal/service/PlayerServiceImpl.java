package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.entity.Player;
import me.bogeun.yajalal.mapper.PlayerMapper;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerInfoDto;
import me.bogeun.yajalal.payload.player.PlayerUpdateDto;
import me.bogeun.yajalal.repository.account.AccountRepository;
import me.bogeun.yajalal.repository.player.PlayerRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final AccountService accountService;
    private final PlayerMapper playerMapper;


    @Override
    public Player createNewPlayer(Long userId, PlayerCreateDto createDto) {
        Player newPlayer = playerMapper.createDtoToEntity(createDto);
        Account account = accountService.getAccountById(userId);
        newPlayer.setAccount(account);

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
    public Player getPlayerByName(String playerName) {
        return playerRepository.findByName(playerName).orElseThrow(() -> new IllegalArgumentException("invalid player name."));
    }

    @Override
    public Player updatePlayerInfo(Long userId, PlayerUpdateDto updateDto) {
        Player player = getPlayerByUserId(userId);
        player.setName(updateDto.getName());
        player.setLength(updateDto.getLength());
        player.setWeight(updateDto.getWeight());
        player.setDescription(updateDto.getDescription());
        player.setMainPosition(updateDto.getMainPosition());
        player.setSubPositions(updateDto.getSubPositions());

        playerRepository.save(player);

        return player;
    }

}
