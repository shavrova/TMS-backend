package com.tms.gateway.security;

import com.tms.gateway.util.JwtTokenUtils;
import com.tms.gateway.util.exception.TokenInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var token = jwtTokenUtils.resolveToken(request);
        try {
            if (token != null && jwtTokenUtils.validateToken(token)) {
                Authentication auth = jwtTokenUtils.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (TokenInvalidException ex) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
            return;
        }

        chain.doFilter(request, response);
    }
}
