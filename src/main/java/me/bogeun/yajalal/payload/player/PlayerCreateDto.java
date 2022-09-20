package me.bogeun.yajalal.payload.player;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.Position;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlayerCreateDto {

    @Length(min = 1, max = 10)
    private String name;

    @Min(80)
    @Max(230)
    private Integer length;

    @Min(80)
    @Max(230)
    private Integer weight;

    @Length(max = 100)
    private String description;

    @NotBlank
    private Position mainPosition;

    private List<Position> subPositions = new ArrayList<>();

}
