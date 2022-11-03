package me.bogeun.yajalal.payload.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostInfoDto {

    private Long id;

    private String title;

    private LocalDateTime createdTime;

    private String writerName;

}
