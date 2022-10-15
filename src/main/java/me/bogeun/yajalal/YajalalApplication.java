package me.bogeun.yajalal;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.account.Account;
import me.bogeun.yajalal.entity.account.Gender;
import me.bogeun.yajalal.entity.league.Team;
import me.bogeun.yajalal.entity.player.Batting;
import me.bogeun.yajalal.entity.player.Pitching;
import me.bogeun.yajalal.entity.player.Player;
import me.bogeun.yajalal.entity.player.Position;
import me.bogeun.yajalal.payload.account.AccountJoinDto;
import me.bogeun.yajalal.payload.player.PlayerCreateDto;
import me.bogeun.yajalal.payload.team.TeamCreateDto;
import me.bogeun.yajalal.repository.player.BattingRepository;
import me.bogeun.yajalal.repository.player.PitchingRepository;
import me.bogeun.yajalal.repository.player.PlayerRepository;
import me.bogeun.yajalal.service.AccountService;
import me.bogeun.yajalal.service.PlayerService;
import me.bogeun.yajalal.service.TeamService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
@SpringBootApplication
public class YajalalApplication implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    private final AccountService accountService;
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private final BattingRepository battingRepository;
    private final PitchingRepository pitchingRepository;

    public static void main(String[] args) {
        SpringApplication.run(YajalalApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        AccountJoinDto joinDto = AccountJoinDto.builder()
                .nickname("야구왕 보근")
                .username("bogeun")
                .password("password!@#")
                .birthDate(LocalDate.of(1995, 5, 30))
                .email("pky2892@gmail.com")
                .gender(Gender.MALE)
                .build();

        Account account = accountService.joinNewAccount(joinDto);

        PlayerCreateDto playerCreateDto = PlayerCreateDto.builder()
                .name("박보근")
                .weight(100)
                .height(200)
                .mainPosition(Position.P)
                .subPositions(Set.of(Position.C, Position.CF, Position.SS))
                .description("야구를 겁나 잘합니다.")
                .build();

        Player player = playerService.createNewPlayer(account.getId(), playerCreateDto);

        TeamCreateDto teamCreateDto = TeamCreateDto.builder()
                .name("꼴데 자이언츠")
                .description("우승을 못하는 팀")
                .limitOfPlayer(10)
                .build();

        Team team = teamService.createNewTeam(teamCreateDto);

        playerService.joinTheClub(player.getId(), team.getId());

        for(int i = 1 ; i <= 9 ; i++) {
            Player p = Player.builder()
                    .name("p" + i)
                    .weight(50 + i)
                    .height(150 + i)
                    .mainPosition(Position.FB)
                    .description(String.format("player%d 입니다.", i))
                    .build();

            Player savedPlayer = playerRepository.save(p);
            playerService.joinTheClub(savedPlayer.getId(), team.getId());

            Batting batting = Batting.builder()
                    .h(i)
                    .HR(i)
                    .build();
            batting.setPlayer(savedPlayer);
            battingRepository.save(batting);

            Pitching pitching = Pitching.builder()
                    .k(i)
                    .w(i)
                    .build();
            pitching.setPlayer(savedPlayer);
            pitchingRepository.save(pitching);
        }
    }
}
