package me.bogeun.yajalal.service.post;

import me.bogeun.yajalal.entity.post.Post;
import me.bogeun.yajalal.payload.post.PostCreateDto;
import me.bogeun.yajalal.payload.post.PostDto;
import me.bogeun.yajalal.payload.post.PostRequestDto;
import me.bogeun.yajalal.payload.post.PostUpdateDto;

import java.util.List;

public interface PostService {
    void createPost(PostCreateDto createDto);

    Post getPost(Long postId);

    PostDto getPostDto(Long postId);

    List<PostDto> getRecentPostList(PostRequestDto requestDto, Integer limit);

    void updatePost(Long postId, PostUpdateDto updateDto);

    void deletePost(Long postId);
}
