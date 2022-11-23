package me.bogeun.yajalal.service.post;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.account.Account;
import me.bogeun.yajalal.entity.post.Post;
import me.bogeun.yajalal.mapper.PostMapper;
import me.bogeun.yajalal.payload.post.PostCreateDto;
import me.bogeun.yajalal.payload.post.PostDto;
import me.bogeun.yajalal.payload.post.PostRequestDto;
import me.bogeun.yajalal.payload.post.PostUpdateDto;
import me.bogeun.yajalal.repository.post.PostRepository;
import me.bogeun.yajalal.service.account.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AccountService accountService;

    private final PostMapper postMapper;

    @Override
    public void createPost(PostCreateDto createDto) {
        Post post = postMapper.createDtoToEntity(createDto);

        Account account = accountService.getAccountById(createDto.getAccountId());

        post.setCreatedTime(LocalDateTime.now());
        post.setAccount(account);

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
    public List<PostDto> getRecentPostList(PostRequestDto requestDto, int limit, int page) {
        List<Post> posts = postRepository.getRecentPostListByType(requestDto.getPostType(), requestDto.getTypeId(), limit, page);

        return posts.stream()
                .map(x -> {
                    PostDto postDto = postMapper.entityToPostDto(x);
                    postDto.setWriterName(x.getAccount().getNickname());
                    return postDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void updatePost(Long postId, PostUpdateDto updateDto) {
        Post post = getPost(postId);

        post.setTitle(updateDto.getTitle());
        post.setContent(updateDto.getContent());
        post.setUpdatedTime(LocalDateTime.now());

        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = getPost(postId);

        postRepository.delete(post);
    }

    @Override
    public Integer getPostCount(PostRequestDto requestDto) {
        return postRepository.getPostCount(requestDto.getPostType(), requestDto.getTypeId());
    }
}
