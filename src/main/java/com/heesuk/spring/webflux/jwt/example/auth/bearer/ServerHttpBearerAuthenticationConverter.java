package com.heesuk.spring.webflux.jwt.example.auth.bearer;

import com.heesuk.spring.webflux.jwt.example.auth.jwt.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This is a Converter that validates TOKEN against requests coming from AuthenticationFilter ServerWebExchange.
 */
public class ServerHttpBearerAuthenticationConverter implements Function<ServerWebExchange, Mono<Authentication>> {

  private static final String BEARER = "Bearer ";
  private static final Predicate<String> matchBearerLength = authValue -> authValue.length() > BEARER.length();
  private static final Function<String, Mono<String>> isolateBearerValue = authValue -> Mono.justOrEmpty(authValue.substring(BEARER.length()));

  private JwtVerifyHandler jwtVerifier = new JwtVerifyHandler();

  @Override
  public Mono<Authentication> apply(ServerWebExchange serverWebExchange) {
    return Mono.justOrEmpty(serverWebExchange)
      .flatMap(AuthorizationHeaderPayload::extract)
      .filter(matchBearerLength)
      .flatMap(isolateBearerValue)
      .flatMap(jwtVerifier::check)
      .flatMap(CurrentUserAuthenticationBearer::create).log();
  }

}
