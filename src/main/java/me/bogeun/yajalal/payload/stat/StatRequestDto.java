package me.bogeun.yajalal.payload.stat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatRequestDto {

    int count;

    String[] stats;

}
