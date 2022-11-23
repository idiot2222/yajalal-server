package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.post.PostCreateDto;
import me.bogeun.yajalal.payload.post.PostDto;
import me.bogeun.yajalal.payload.post.PostRequestDto;
import me.bogeun.yajalal.payload.post.PostUpdateDto;
import me.bogeun.yajalal.payload.response.ResponseDto;
import me.bogeun.yajalal.service.post.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPost(@RequestBody PostCreateDto createDto) {
        postService.createPost(createDto);

        return ResponseEntity
                .ok()
                .body(new ResponseDto("ok"));
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<ResponseDto> getPost(@PathVariable Long postId) {
        PostDto postDto = postService.getPostDto(postId);

        return ResponseEntity
                .ok()
                .body(new ResponseDto(postDto, "ok"));
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> getPosts(@RequestParam int page,
                                                @RequestParam int limit,
                                                PostRequestDto requestDto) {

        List<PostDto> postList = postService.getRecentPostList(requestDto, limit, page);

        return ResponseEntity
                .ok()
                .body(new ResponseDto(postList));
    }

    @GetMapping("/list/count")
    public ResponseEntity<ResponseDto> getPostCount(PostRequestDto requestDto) {
        Integer count = postService.getPostCount(requestDto);

        return ResponseEntity
                .ok()
                .body(new ResponseDto(count));
    }

    @PostMapping("/update/{postId}")
    public ResponseEntity<ResponseDto> updatePost(@PathVariable Long postId,
                                                  @RequestBody PostUpdateDto updateDto) {
        postService.updatePost(postId, updateDto);

        return ResponseEntity
                .ok(new ResponseDto("ok"));
    }

    @PostMapping("/delete/{postId}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return ResponseEntity
                .ok(new ResponseDto("ok"));
    }

}
