package me.bogeun.yajalal.service.post;

import me.bogeun.yajalal.entity.post.Post;
import me.bogeun.yajalal.payload.post.PostCreateDto;
import me.bogeun.yajalal.payload.post.PostDto;
import me.bogeun.yajalal.payload.post.PostRequestDto;

import java.util.List;

public interface PostService {
    void createPost(PostCreateDto createDto);

    Post getPost(Long postId);

    PostDto getPostDto(Long postId);

    List<PostDto> getRecentPostList(PostRequestDto requestDto, Integer limit);
}
