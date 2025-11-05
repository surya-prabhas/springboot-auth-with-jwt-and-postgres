package com.prabhas.SpringSecurity.config;

import com.prabhas.SpringSecurity.service.JWTService;
import com.prabhas.SpringSecurity.service.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
       //Bearer  eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2hpdCIsImlhdCI6MTc2MjMzOTAyMSwiZXhwIjoxNzYyMzM5MTI5fQ.0g21bhcx3TRaZs8ROfajTfcIsbNcnEBqthjasir2Apo
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(username);

             if(jwtService.validateToken(token, userDetails)) {
                 UsernamePasswordAuthenticationToken authToken =
                         new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        // continue the filter chain
        filterChain.doFilter(request, response);

    }
}
