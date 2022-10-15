package me.bogeun.yajalal.entity.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.bogeun.yajalal.entity.player.Player;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 20, nullable = false)
    private String username;

    @Column(length = 68, nullable = false)
    private String password;

    @Column(unique = true, length = 10, nullable = false)
    private String nickname;

    @Column(unique = true, length = 45, nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;


    @Builder
    public Account(Long id, String username, String password, String nickname, String email, LocalDate birthDate, Gender gender, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.role = role;
    }
}
