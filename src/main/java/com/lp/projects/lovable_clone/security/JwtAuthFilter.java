package com.lp.projects.lovable_clone.security;

import com.lp.projects.lovable_clone.dto.security.JwtUserPrincipal;
import com.lp.projects.lovable_clone.error.BadRequestException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtAuthFilter extends OncePerRequestFilter {

    AuthUtil authUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("Incoming request: {}", request.getRequestURI());

        final String headerJwtToken = request.getHeader("Authorization");
        if (headerJwtToken == null || !headerJwtToken.startsWith("Bearer ")) {
            log.error("JWT token is not present in request!!!");
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtToken = headerJwtToken.substring(7); // Remove "Bearer " prefix
        JwtUserPrincipal userPrincipal = authUtil.validateToken(jwtToken);

        if(userPrincipal != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.authorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            log.error("Invalid JWT token!!!");
            throw new BadRequestException("Invalid JWT token");
        }

        filterChain.doFilter(request, response);
    }
}
