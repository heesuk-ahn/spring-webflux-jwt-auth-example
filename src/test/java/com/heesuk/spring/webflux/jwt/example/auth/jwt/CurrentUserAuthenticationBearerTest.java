package com.heesuk.spring.webflux.jwt.example.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.springframework.security.core.Authentication;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CurrentUserAuthenticationBearerTest {

  private final JwtVerifyHandler handler = new JwtVerifyHandler();

  private final Integer userId = 1;
  private final String validToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoiYWNjZXNzVG9rZW4iLCJ1c2VySWQiOjF9.0pN7XH3qPkIHr_z3vypIE09zy0oQbHiODlyfpOEO3A4";

  @Test
  public void shouldGetCurrentUserAuth() {
    DecodedJWT decodedJWT = handler.check(validToken).block(Duration.ofSeconds(3));

    Authentication authentication = CurrentUserAuthenticationBearer.create(decodedJWT).block(Duration.ofSeconds(3));

    assertEquals(authentication.getPrincipal(), userId);
  }

}
