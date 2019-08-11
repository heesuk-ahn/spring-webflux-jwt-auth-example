package com.heesuk.spring.webflux.jwt.example.auth.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JwtVerifyHandlerTest {

  private final JwtVerifyHandler jwtVerifyHandler = new JwtVerifyHandler();

  private final String validAccessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInR5cGUiOiJhY2Nlc3NUb2tlbiJ9.6b72oS0nCDDOuiU6bAfx8H9kd5rTk6dKTykIKNskHiQ";
  private final String invalidToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInR5cGUiOiJhY2Nlc3NUb2tlbiJ9.iGfnGcP3mDshzNdJAZD0bCnp4mCw3_UCn5HIHw_j-4E";

  @Test
  public void shouldVerifyToken() {
    DecodedJWT decodedJWT = jwtVerifyHandler.check(validAccessToken).block(Duration.ofSeconds(3));

    assertThat(decodedJWT.getToken(), is(validAccessToken));
  }

  @Test(expected= SignatureVerificationException.class)
  public void shouldThrowSignatureVerificationExceptionIfInvalidUserToken() {
    DecodedJWT decodedJWT = jwtVerifyHandler.check(invalidToken).block(Duration.ofSeconds(3));
  }

}
