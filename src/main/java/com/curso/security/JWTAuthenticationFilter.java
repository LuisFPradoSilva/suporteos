package com.curso.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.curso.services.UserDetailServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final UserDetailServiceImpl userDetailServiceImpl;

    public JWTAuthenticationFilter(JWTUtils jwtUtils, UserDetailServiceImpl userDetailServiceImpl) {
        this.jwtUtils = jwtUtils;
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (token != null && jwtUtils.isTokenValid(token)) {
            String username = jwtUtils.getUsername(token);
            var userDetails = userDetailServiceImpl.loadUserByUsername(username);
            var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }


    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
