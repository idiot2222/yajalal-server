package me.bogeun.yajalal.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(unique = true, length = 20)
    private String username;

    @Column(length = 68)
    private String password;

    @Column(unique = true, length = 45)
    private String email;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isBirthDatePublic;

    private boolean isGenderPublic;


    @Builder
    public Account(Long id, String username, String password, String email, LocalDate birthDate, Gender gender, Role role, boolean isBirthDatePublic, boolean isGenderPublic) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.role = role;
        this.isBirthDatePublic = isBirthDatePublic;
        this.isGenderPublic = isGenderPublic;
    }
}
