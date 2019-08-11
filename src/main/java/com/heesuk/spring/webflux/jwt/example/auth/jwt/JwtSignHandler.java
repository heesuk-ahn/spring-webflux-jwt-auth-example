package com.heesuk.spring.webflux.jwt.example.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtSignHandler {

  public String createAccessToken(Optional<String> typeO, Integer userId) {
    String type = typeO.orElse("accessToken");
    Algorithm algorithm = Algorithm.HMAC256(JwtSecrets.DEFAULT_SECRET);
    String token = JWT.create()
      .withClaim("type", type)
      .withClaim("userId", userId)
      .sign(algorithm);
    return token;
  }

}
