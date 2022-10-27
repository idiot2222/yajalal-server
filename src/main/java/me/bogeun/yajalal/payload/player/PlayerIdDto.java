package me.bogeun.yajalal.payload.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerIdDto {

    private Long id;

    private String name;

    private Integer backNumber;

}
