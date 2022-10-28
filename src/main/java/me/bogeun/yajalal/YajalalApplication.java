package me.bogeun.yajalal;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.league.LeagueDto;
import me.bogeun.yajalal.repository.player.BattingRepository;
import me.bogeun.yajalal.repository.player.PitchingRepository;
import me.bogeun.yajalal.repository.player.PlayerRepository;
import me.bogeun.yajalal.service.AccountService;
import me.bogeun.yajalal.service.LeagueService;
import me.bogeun.yajalal.service.PlayerService;
import me.bogeun.yajalal.service.TeamService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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
    private final LeagueService leagueService;

    public static void main(String[] args) {
        SpringApplication.run(YajalalApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
//        AccountJoinDto joinDto = AccountJoinDto.builder()
//                .nickname("야구왕 보근")
//                .username("bogeun")
//                .password("password!@#")
//                .birthDate(LocalDate.of(1995, 5, 30))
//                .email("pky2892@gmail.com")
//                .gender(Gender.MALE)
//                .build();
//
//        Account account = accountService.joinNewAccount(joinDto);
//
//        PlayerCreateDto playerCreateDto = PlayerCreateDto.builder()
//                .name("박보근")
//                .weight(100)
//                .height(200)
//                .mainPosition(Position.P)
//                .subPositions(Set.of(Position.C, Position.CF, Position.SS))
//                .description("야구를 겁나 잘합니다.")
//                .build();
//
//        Player player = playerService.createNewPlayer(account.getId(), playerCreateDto);

//        TeamCreateDto teamCreateDto = TeamCreateDto.builder()
//                .name("이글스")
//                .description("이글스")
//                .limitOfPlayer(10)
//                .build();
//
//        Team team = teamService.createNewTeam(teamCreateDto);

//        playerService.joinTheClub(player.getId(), team.getId());.

//        Random random = new Random();
//        for(int i = 1 ; i <= 9 ; i++) {
//            Player p = Player.builder()
//                    .name(team.getName() + i)
//                    .weight(50 + i)
//                    .height(150 + i)
//                    .mainPosition(Position.FB)
//                    .description(String.format("%s%d 입니다.", team.getName(), i))
//                    .build();
//
//            Player savedPlayer = playerRepository.save(p);
//            playerService.joinTheClub(savedPlayer.getId(), team.getId());
//
//            Batting batting = Batting.builder()
//                    .h(random.nextInt(100))
//                    .r(random.nextInt(50))
//                    .RBI(random.nextInt(50))
//                    .HR(random.nextInt(20))
//                    .build();
//            batting.setPlayer(savedPlayer);
//            battingRepository.save(batting);
//
//            Pitching pitching = Pitching.builder()
//                    .k(random.nextInt(100))
//                    .w(random.nextInt(10))
//                    .h(random.nextInt(15))
//                    .SV(random.nextInt(15))
//                    .build();
//            pitching.setPlayer(savedPlayer);
//            pitchingRepository.save(pitching);
//        }

//        LeagueDto leagueDto = LeagueDto.builder()
//                .name("KBO")
//                .description("한국의 프로야구")
//                .limitOfTeam(8)
//                .build();
//
//        leagueService.createNewLeague(leagueDto);
    }
}
