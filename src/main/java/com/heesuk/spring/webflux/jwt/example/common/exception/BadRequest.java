package com.heesuk.spring.webflux.jwt.example.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {

  private final String message;

  public BadRequest(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}