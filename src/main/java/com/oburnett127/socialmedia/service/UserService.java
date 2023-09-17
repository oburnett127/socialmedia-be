package com.oburnett127.socialmedia.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import com.oburnett127.socialmedia.SocialmediaApplication;
import com.oburnett127.socialmedia.model.UserInfo;
import com.oburnett127.socialmedia.model.request.AuthenticationRequest;
import com.oburnett127.socialmedia.model.request.RegisterRequest;
import com.oburnett127.socialmedia.model.response.AuthenticationResponse;
import com.oburnett127.socialmedia.repository.UserInfoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserInfoRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final AmqpAdmin amqpAdmin;
  private final TopicExchange topicExchange;

  @Autowired
  private ConnectionFactory connectionFactory;

  @SneakyThrows
  public AuthenticationResponse register(RegisterRequest request) {
    var user = UserInfo.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .roles("USER")
        .build();
    userRepository.save(user);
    createUserQueue(user.getId(), connectionFactory);
    var jwtToken = jwtService.generateToken(user.getEmail());
    return AuthenticationResponse.builder()
        .token(jwtToken.toString())
        .build();
  }

  public void createUserQueue(int userId, ConnectionFactory connectionFactory) {
    String queueName = "user_queue_" + userId;
    Queue queue = new Queue(queueName, true, false, false);

    amqpAdmin.declareExchange(topicExchange);
    amqpAdmin.declareQueue(queue);

    Binding binding = BindingBuilder.bind(queue).to(topicExchange)
                                    .with(SocialmediaApplication.ROUTING_KEY_PREFIX + userId);
    amqpAdmin.declareBinding(binding);

    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);

    MessageListenerAdapter adapter = new MessageListenerAdapter(new Object() {
        public void handleMessage(String message) {
            System.out.println("Received message: " + message);
        }
    });

    container.setMessageListener(adapter);
    container.start();
  }

  @SneakyThrows
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    UserInfo user = null;
    String jwtToken = null;
  
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
      );
      user = userRepository.findByEmail(request.getEmail())
          .orElseThrow();
      jwtToken = jwtService.generateToken(user.getEmail());
    } catch(Exception e) {
      System.out.println("Exception occurred in UserService.authenticate() - exception: " + e.getMessage());
      AuthenticationResponse response = null;
      return response;
    }
  
    //revokeAllUserTokens(user);
    //saveUserToken(user, jwtToken);
  
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
  
  // private void saveUserToken(User user, String jwtToken) {
  //   var token = Token.builder()
  //       .user(user)
  //       .token(jwtToken)
  //       .tokenType(TokenType.BEARER)
  //       .expired(false)
  //       .revoked(false)
  //       .build();
  //   tokenRepository.save(token);
  // }

  // private void revokeAllUserTokens(User user) {
  //   var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
  //   if (validUserTokens.isEmpty())
  //     return;
  //   validUserTokens.forEach(token -> {
  //     token.setExpired(true);
  //     token.setRevoked(true);
  //   });
  //   tokenRepository.saveAll(validUserTokens);
  // }

  @SneakyThrows
  public String getRoleByUserId(int userId) {
    Optional<UserInfo> user = userRepository.findById(userId);
    UserInfo account = user.get();
    return account.getRoles();
  }

  @SneakyThrows
  public int getUserIdByEmail(String emailAddress) {
    Optional<UserInfo> user = userRepository.findByEmail(emailAddress);
    int userId = user.get().getId();
    return userId;
  }

  @SneakyThrows
  public Optional<UserInfo> getUserByEmail(String emailAddress) {
    return userRepository.findByEmail(emailAddress);
  }

  @SneakyThrows
  public List<UserInfo> getUserByName(String name) {
    String[] nameParts = name.split(" ");

    if(nameParts.length == 2) {
      String firstName = nameParts[0];
      String lastName = nameParts[1];
      return userRepository.findByFullName(firstName, lastName);
    } else if(nameParts.length == 1) {
      String providedName = nameParts[0];
      List<UserInfo> matchingUsers = userRepository.findByFirstName(providedName);
      matchingUsers.addAll(userRepository.findByLastName(providedName));
      return matchingUsers;
    }

    return null;
  }

  @SneakyThrows
  public List<UserInfo> getUsers(List<Integer> userIds) {
    return userIds.stream()
                .map(userId -> userRepository.findById(userId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
  }
  
  @SneakyThrows
  public Optional<UserInfo> getUserByUserId(int userId) {
    return userRepository.findById(userId);
  }
}
