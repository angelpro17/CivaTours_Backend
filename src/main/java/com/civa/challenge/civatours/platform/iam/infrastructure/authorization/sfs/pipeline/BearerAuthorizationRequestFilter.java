package com.civa.challenge.civatours.platform.iam.infrastructure.authorization.sfs.pipeline;

import com.civa.challenge.civatours.platform.iam.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import com.civa.challenge.civatours.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {

  private static final Logger LOGGER
      = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);
  private final BearerTokenService tokenService;

  @Qualifier("defaultUserDetailsService")
  private final UserDetailsService userDetailsService;

  public BearerAuthorizationRequestFilter(BearerTokenService tokenService,
      UserDetailsService userDetailsService) {
    this.tokenService = tokenService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    String requestPath = request.getRequestURI();
    LOGGER.info("BearerAuthorizationRequestFilter - Processing request: {}", requestPath);
    
    if (requestPath.startsWith("/api/v1/authentication/") || 
        requestPath.startsWith("/v3/api-docs/") || 
        requestPath.startsWith("/swagger-ui") || 
        requestPath.startsWith("/swagger-resources/") || 
        requestPath.startsWith("/webjars/")) {
      LOGGER.info("BearerAuthorizationRequestFilter - Skipping authentication for public endpoint: {}", requestPath);
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String token = tokenService.getBearerTokenFrom(request);
      LOGGER.info("Token: {}", token);
      if (token != null && tokenService.validateToken(token)) {
        String email = tokenService.getEmailByToken(token);
        var userDetails = userDetailsService.loadUserByUsername(email);
        SecurityContextHolder.getContext()
            .setAuthentication(
                UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));
      }
      else {
        LOGGER.info("Token is not valid");
      }

    } catch (Exception e) {
      LOGGER.error("Cannot set user authentication: {}", e.getMessage());
    }
    filterChain.doFilter(request, response);
  }
}
