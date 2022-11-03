package me.bogeun.yajalal.service.post;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.post.Post;
import me.bogeun.yajalal.mapper.PostMapper;
import me.bogeun.yajalal.payload.post.PostCreateDto;
import me.bogeun.yajalal.payload.post.PostDto;
import me.bogeun.yajalal.payload.post.PostRequestDto;
import me.bogeun.yajalal.repository.post.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    @Override
    public void createPost(PostCreateDto createDto) {
        Post post = postMapper.createDtoToEntity(createDto);

        post.setCreatedTime(LocalDateTime.now());

        postRepository.save(post);
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("invalid post id."));
    }

    @Override
    public PostDto getPostDto(Long postId) {
        Post post = getPost(postId);

        return postMapper.entityToPostDto(post);
    }

    @Override
    public List<PostDto> getRecentPostList(PostRequestDto requestDto, Integer limit) {
        List<Post> posts = postRepository.getRecentPostListByType(requestDto.getPostType(), requestDto.getTypeId(), limit);

        return posts.stream()
                .map(postMapper::entityToPostDto)
                .collect(Collectors.toList());
    }
}
