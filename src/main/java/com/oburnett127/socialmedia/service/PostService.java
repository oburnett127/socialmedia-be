package com.oburnett127.socialmedia.service;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.oburnett127.socialmedia.messaging.MessageConsumerService;
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
    private final RabbitService rabbitService;
    
    @Lazy
    @Autowired
    private MessageConsumerService messageConsumerService;

    public PostService(PostRepository postRepository, UserService userService, FriendService friendService, RabbitTemplate rabbitTemplate, RabbitService rabbitService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.friendService = friendService;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitService = rabbitService;
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
    public void createPost(Post post) {
        postRepository.save(post);
        UserInfo user = userService.getUserByUserId(post.getAuthorUserId()).get();
        String message = user.getFirstName() + " " + user.getLastName() + " made a new post";
        //The message consists of the first and last name of the post author. The routing key
        //will be for the queue of a different friend of the post author for each iteration of loop.
        List<Integer> friendUserIds = friendService.getFriendUserIds(post.getAuthorUserId());

        for (int userId : friendUserIds) {
            String queueName = "user." + userId;
            rabbitService.registerQueue(queueName);
            rabbitTemplate.convertAndSend("post_exchange", queueName, message);
        }
    }
}
