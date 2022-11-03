package me.bogeun.yajalal.payload.post;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.post.PostType;

@Getter
@Setter
public class PostRequestDto {

    private Long typeId;

    private PostType postType;

}
