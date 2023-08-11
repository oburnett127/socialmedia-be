package com.oburnett127.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserInfo {
  @Builder.Default
  private long serialVersionUid = 1234L;

  @Id
  @GeneratedValue
  private Integer id;
  @Basic(optional = false)
  private String firstName;
  @Basic(optional = false)
  private String lastName;
  @Basic(optional = false)
  private String email;
  @Basic(optional = false)
  private String password;
  private String roles;
}
  