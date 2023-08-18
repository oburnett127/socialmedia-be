package com.oburnett127.socialmedia.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.oburnett127.socialmedia.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
  
  Optional<UserInfo> findByEmail(String email);

  @Query("SELECT u FROM UserInfo u WHERE u.firstName = :firstName AND u.lastName = :lastName")
  List<UserInfo> findByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);

  @Query("SELECT u FROM UserInfo u WHERE u.firstName = :firstName")
  List<UserInfo> findByFirstName(@Param("firstName") String firstName);
  
  @Query("SELECT u FROM UserInfo u WHERE u.lastName = :lastName")
  List<UserInfo> findByLastName(@Param("lastName") String lastName);
}
