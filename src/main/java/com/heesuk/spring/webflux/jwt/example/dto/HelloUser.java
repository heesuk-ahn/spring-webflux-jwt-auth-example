package com.heesuk.spring.webflux.jwt.example.dto;

import lombok.Getter;

@Getter
public class HelloUser {

  private final int userId;
  private final String message;

  public HelloUser(int userId) {
    this.userId = userId;
    this.message = "HELLO WORLD! " + userId;
  }

}
