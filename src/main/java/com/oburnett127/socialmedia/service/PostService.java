package com.oburnett127.socialmedia.service;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oburnett127.socialmedia.model.Post;
import com.oburnett127.socialmedia.model.UserInfo;
import com.oburnett127.socialmedia.repository.PostRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final FriendService friendService;
    private final RabbitTemplate rabbitTemplate;

    public PostService(PostRepository postRepository, UserService userService, FriendService friendService, RabbitTemplate rabbitTemplate) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.friendService = friendService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @SneakyThrows
    public Post getOnePost(int postId) {
        final Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) return post.get();
        else return null;
    }

    @SneakyThrows
    public List<Post> getPostsByAuthorUserId(int authorUserId) {
        return postRepository.findByAuthorUserId(authorUserId);
    }

    @SneakyThrows
    public List<Post> getPostsByProfileUserId(int profileUserId) {
        return postRepository.findByProfileUserId(profileUserId);
    }

    @SneakyThrows
    @Transactional
    public void createPost(Post post) {
        postRepository.save(post);
        UserInfo user = userService.getUserByUserId(post.getAuthorUserId()).get();
        String message = user.getFirstName() + " " + user.getLastName() + " made a new post";
        List<Integer> friendUserIds = friendService.getFriendUserIds(post.getAuthorUserId());

        for (int userId : friendUserIds) {
            String queueName = "user_queue_" + userId;
            System.out.println("queue name is: " + queueName);
            rabbitTemplate.convertAndSend("post_exchange", queueName, message);
        }
    }
}
