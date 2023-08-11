package com.oburnett127.socialmedia.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
    var jwtToken = jwtService.generateToken(user.getEmail());
    //saveUserToken(savedUser, jwtToken);

    return AuthenticationResponse.builder()
        .token(jwtToken.toString())
        .build();
  }

  @SneakyThrows
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user.getEmail());
    //revokeAllUserTokens(user);
    //saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken.toString())
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
