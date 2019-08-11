package com.heesuk.spring.webflux.jwt.example.auth.bearer;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

/**
 * BearerTokenReactiveAuthenticationManager is used in AuthenticationFilter.
 */
public class BearerTokenReactiveAuthenticationManager implements ReactiveAuthenticationManager {

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    return Mono.just(authentication);
  }

}
