package com.heesuk.spring.webflux.jwt.example.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED)
public class Unauthorized extends RuntimeException {

  private final String message;

  public Unauthorized(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
