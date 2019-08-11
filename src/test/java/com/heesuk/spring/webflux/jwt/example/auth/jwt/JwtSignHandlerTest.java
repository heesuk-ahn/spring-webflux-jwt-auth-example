package com.heesuk.spring.webflux.jwt.example.auth.jwt;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JwtSignHandlerTest {

  private final JwtSignHandler jwtSignHandler =  new JwtSignHandler();

  @Test
  public void shouldCreateJwtToken() {
    String createdToken = jwtSignHandler.createAccessToken(Optional.empty(), 1);
    final String expectedJwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoiYWNjZXNzVG9rZW4iLCJ1c2VySWQiOjF9.0pN7XH3qPkIHr_z3vypIE09zy0oQbHiODlyfpOEO3A4";

    assertThat(createdToken, is(expectedJwtToken));
  }

}
