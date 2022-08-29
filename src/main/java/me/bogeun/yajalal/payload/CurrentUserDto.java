package me.bogeun.yajalal.payload;

import lombok.*;
import me.bogeun.yajalal.entity.Gender;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserDto {

    private String username;

    private String email;

    private LocalDate birthDate;

    private Gender gender;

}
