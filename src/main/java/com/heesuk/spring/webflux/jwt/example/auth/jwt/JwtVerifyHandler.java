package com.heesuk.spring.webflux.jwt.example.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.heesuk.spring.webflux.jwt.example.common.exception.Unauthorized;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class JwtVerifyHandler {

  private final Algorithm algorithm = Algorithm.HMAC256(JwtSecrets.DEFAULT_SECRET);

  public Mono<DecodedJWT> check(String accessToken) {
    return Mono.just(JWT.require(algorithm).build().verify(accessToken)).log()
      .onErrorResume(e -> Mono.error(new Unauthorized("invalid user token")));
  }

}
