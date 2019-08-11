package com.heesuk.spring.webflux.jwt.example.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY)
public class Unprocessable extends RuntimeException {

  private final String message;

  public Unprocessable(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
