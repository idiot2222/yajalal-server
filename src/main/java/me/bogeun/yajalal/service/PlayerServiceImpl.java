package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.Account;
import me.bogeun.yajalal.entity.Player;
import me.bogeun.yajalal.mapper.PlayerMapper;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.player.PlayerUpdateDto;
import me.bogeun.yajalal.repository.account.AccountRepository;
import me.bogeun.yajalal.repository.player.PlayerRepository;
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
    public Player getPlayerByName(String playerName) {
        return playerRepository.findByName(playerName).orElseThrow(() -> new IllegalArgumentException("invalid player name."));
    }

    @Override
    public Player updatePlayerInfo(Long playerId, PlayerUpdateDto updateDto) {
        Player player = getPlayerById(playerId);
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
