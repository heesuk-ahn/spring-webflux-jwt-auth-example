package com.heesuk.spring.webflux.jwt.example.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

@Slf4j
public class CurrentUserAuthenticationBearer {

  public static Mono<Authentication> create(DecodedJWT decodedJWT) {

    String type;
    Integer userId;

    try {
      type = decodedJWT.getClaims().get("type").asString();
      userId = decodedJWT.getClaims().get("userId").asInt();
    } catch (Exception e) {
      log.error("json token parse error. invalid claims decoded JWT : " + decodedJWT.getClaims());
      return Mono.empty();
    }

    return Mono.justOrEmpty(new CurrentUserAuthenticationToken(type, userId, decodedJWT));
  }

}