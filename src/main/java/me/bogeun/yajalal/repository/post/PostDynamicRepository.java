package me.bogeun.yajalal.repository.post;

import me.bogeun.yajalal.entity.post.Post;
import me.bogeun.yajalal.entity.post.PostType;
import me.bogeun.yajalal.payload.post.PostDto;

import java.util.List;

public interface PostDynamicRepository {

    List<Post> getRecentPostListByType(PostType postType, Long typeId, Integer limit);

}
