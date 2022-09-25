package me.bogeun.yajalal.payload;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private Object content;
    private String message;

    public ResponseDto(String message) {
        this.message = message;
    }
}
