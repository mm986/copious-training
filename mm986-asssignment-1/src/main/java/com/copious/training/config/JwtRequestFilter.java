package com.copious.training.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@Component
@Log4j2
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final String uri = request.getRequestURI();

            if (uri.contains("swagger") || uri.contains("v2") || uri.contains("authenticate")) {
                log.info("JWT-AUTH: Skipping token validation for {}", uri);
                chain.doFilter(request, response);
            } else {
                String jwtToken = extractJwtFromRequest(request);
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
                if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(
                                    new UsernamePasswordAuthenticationToken(userDetails,
                                            null,
                                            userDetails.getAuthorities())
                            );
                    log.info("JWT-AUTH: Successfully validated JWT token for {}", uri);
                    chain.doFilter(request, response);
                } else {
                    log.error("JWT-AUTH:  Missing authentication token.");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing authentication token.");
                }
            }
        } catch (Exception ex) {
            log.error("JWT-AUTH:  Malformed JWT Token.");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Malformed JWT Token. JWT strings must contain exactly 2 period characters. Found: 0");
        }
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        return Stream.of(request)
                .map(req -> req.getHeader("Authorization"))
                .filter(token -> StringUtils.hasText(token))
                .findFirst()
                .orElse(null);
    }
}

