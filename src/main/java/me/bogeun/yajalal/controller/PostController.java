package me.bogeun.yajalal.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.payload.post.PostCreateDto;
import me.bogeun.yajalal.payload.post.PostDto;
import me.bogeun.yajalal.payload.post.PostRequestDto;
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
    public ResponseEntity<ResponseDto> getPosts(@RequestParam Integer limit,
                                                PostRequestDto requestDto) {

        List<PostDto> postList = postService.getRecentPostList(requestDto, limit);

        return ResponseEntity
                .ok()
                .body(new ResponseDto(postList));
    }



}
