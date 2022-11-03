package me.bogeun.yajalal.payload.post;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.post.PostType;

@Getter
@Setter
public class PostCreateDto {

    private String title;
    private String content;
    private PostType postType;
    private Long accountId;
    private Long typeId;

}
