package com.heesuk.spring.webflux.jwt.example.controller;


import com.heesuk.spring.webflux.jwt.example.dto.HelloUser;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Objects;

@CrossOrigin
@Slf4j
@RestController
public class HelloController {

  private final Mono<SecurityContext> context  = ReactiveSecurityContextHolder.getContext();

  private Mono<Integer> extractUserSeqIdFromJwtToken(Mono<SecurityContext> context) {
    return context.filter(c -> Objects.nonNull(c.getAuthentication()))
      .map(s -> s.getAuthentication().getPrincipal())
      .cast(Integer.class);
  }

  @GetMapping("/v1/hello")
  public Mono<HelloUser> getHello() {
    return extractUserSeqIdFromJwtToken(context).flatMap(userId -> Mono.just(new HelloUser(userId)));
  }

}
