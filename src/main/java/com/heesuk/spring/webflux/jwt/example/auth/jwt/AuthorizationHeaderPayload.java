package com.heesuk.spring.webflux.jwt.example.auth.jwt;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class AuthorizationHeaderPayload {

  public static Mono<String> extract(ServerWebExchange serverWebExchange) {
    return Mono.justOrEmpty(serverWebExchange.getRequest()
      .getHeaders()
      .getFirst(HttpHeaders.AUTHORIZATION));
  }

}