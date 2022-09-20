package me.bogeun.yajalal.payload.player;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PlayerResponse {

    private Object content;
    private String message;

    public PlayerResponse(String message) {
        this.message = message;
    }
}
