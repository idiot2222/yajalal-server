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

    private Long id;

    private String username;

    private String email;

}
