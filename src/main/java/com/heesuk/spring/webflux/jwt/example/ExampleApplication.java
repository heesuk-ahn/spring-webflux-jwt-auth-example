package com.heesuk.spring.webflux.jwt.example;

import com.heesuk.spring.webflux.jwt.example.auth.bearer.BearerTokenReactiveAuthenticationManager;
import com.heesuk.spring.webflux.jwt.example.auth.bearer.ServerHttpBearerAuthenticationConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@SpringBootApplication
public class ExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleApplication.class, args);
  }

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

    //Disable things you don't need in spring security.
    http.httpBasic().disable();
    http.formLogin().disable();
    http.csrf().disable();
    http.logout().disable();

    //Those that do not require jwt token authentication should be pass.
    http.authorizeExchange().pathMatchers("/", "/v1/users/signUp", "/v1/users/login").permitAll();
    http.authorizeExchange().pathMatchers(HttpMethod.OPTIONS).permitAll();
    http.authorizeExchange().pathMatchers(HttpMethod.GET).permitAll();
    http.authorizeExchange().pathMatchers(HttpMethod.POST).permitAll();

    //Apply a JWT custom filter to all / ** apis.
    http.authorizeExchange()
      .pathMatchers("/**")
      .authenticated()
      .and()
      .addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
      .httpBasic().disable()
      .formLogin().disable()
      .csrf().disable()
      .logout().disable()
      .cors();

    return http.build();
  }

  /**
   * Spring security works by filter chaning.
   * We need to add a JWT CUSTOM FILTER to the chain.
   *
   * what is AuthenticationWebFilter:
   *
   *  A WebFilter that performs authentication of a particular request. An outline of the logic:
   *  A request comes in and if it does not match setRequiresAuthenticationMatcher(ServerWebExchangeMatcher),
   *  then this filter does nothing and the WebFilterChain is continued.
   *  If it does match then... An attempt to convert the ServerWebExchange into an Authentication is made.
   *  If the result is empty, then the filter does nothing more and the WebFilterChain is continued.
   *  If it does create an Authentication...
   *  The ReactiveAuthenticationManager specified in AuthenticationWebFilter(ReactiveAuthenticationManager) is used to perform authentication.
   *  If authentication is successful, ServerAuthenticationSuccessHandler is invoked and the authentication is set on ReactiveSecurityContextHolder,
   *  else ServerAuthenticationFailureHandler is invoked
   *
   */
  private AuthenticationWebFilter bearerAuthenticationFilter(){
    AuthenticationWebFilter bearerAuthenticationFilter;

    Function<ServerWebExchange, Mono<Authentication>> bearerConverter;

    ReactiveAuthenticationManager authManager;
    authManager  = new BearerTokenReactiveAuthenticationManager();
    bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
    bearerConverter = new ServerHttpBearerAuthenticationConverter();

    bearerAuthenticationFilter.setAuthenticationConverter(bearerConverter);
    bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));

    return bearerAuthenticationFilter;
  }

}
