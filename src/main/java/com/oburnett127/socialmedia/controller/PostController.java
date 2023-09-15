package com.oburnett127.socialmedia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.oburnett127.socialmedia.model.Post;
import com.oburnett127.socialmedia.model.dto.PostDto;
import com.oburnett127.socialmedia.model.request.PostCreateRequest;
import com.oburnett127.socialmedia.service.PostService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/getonepost/{postId}")
    public ResponseEntity<PostDto> getOnePost(@Validated @PathVariable int postId) {
        System.out.println("inside PostController.getOnePost() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        final var post = service.getOnePost(postId);
        var result = new PostDto(postId, post.getAuthorUserId(), post.getProfileUserId(), post.getText());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getbyauthor/{authorUserId}")
    public ResponseEntity<PostDto[]> getPostsByAuthorUserId(@Validated @PathVariable String userId) {
        System.out.println("inside PostController.getPostsByAuthorUserId() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        List<Post> posts = service.getPostsByAuthorUserId(Integer.parseInt(userId));

        var result = posts.stream()
                    .map(post -> new PostDto(post.getPostId(), post.getAuthorUserId(), post.getProfileUserId(), post.getText()))
                    .collect(Collectors.toList());

        PostDto[] postArray = result.toArray(new PostDto[0]);                    

        return new ResponseEntity<>(postArray, HttpStatus.OK);
    }

    @GetMapping("/getbyprofile/{id}")
    public ResponseEntity<PostDto[]> getPostsByProfileUserId(@Validated @PathVariable String id) {
        System.out.println("inside PostController.getPostsByProfileUserId() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        List<Post> posts = service.getPostsByProfileUserId(Integer.parseInt(id));

        var result = posts.stream()
                    .map(post -> new PostDto(post.getPostId(), post.getAuthorUserId(), post.getProfileUserId(), post.getText()))
                    .collect(Collectors.toList());

        PostDto[] postArray = result.toArray(new PostDto[0]);                    

        return new ResponseEntity<>(postArray, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@Validated @RequestBody PostCreateRequest postCreateRequest ) throws IOException {
        System.out.println("inside PostController.createPost() +-+-+-+-&&&&&&&&&&&&&&&&&&+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
        final Post post = Post.builder()
                .authorUserId(postCreateRequest.getAuthorUserId())
                .profileUserId(postCreateRequest.getProfileUserId())
                .text(postCreateRequest.getText())
                .build();
        service.createPost(post);

        return ResponseEntity.ok().body(null);
    }
}