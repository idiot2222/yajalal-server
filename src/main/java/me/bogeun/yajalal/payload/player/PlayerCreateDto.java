package me.bogeun.yajalal.payload.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.player.Position;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Builder
public class PlayerCreateDto {

    @Length(min = 1, max = 10)
    private String name;

    @Min(50)
    @Max(230)
    private Integer height;

    @Min(30)
    @Max(200)
    private Integer weight;

    @Length(max = 100)
    private String description;

    @NotNull
    private Position mainPosition;

    private Set<Position> subPositions;

}
