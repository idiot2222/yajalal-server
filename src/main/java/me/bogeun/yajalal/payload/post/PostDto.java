package me.bogeun.yajalal.payload.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {

    private String title;

    private String content;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private String writerName;

}
