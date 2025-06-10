package com.eventhub.eventhub_backend.security;


import com.eventhub.eventhub_backend.service.CustomUserDetailsService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter extends GenericFilter {

    private final UserDetails userDetails;

    private final JwtService jwtService;

    private final CustomUserDetailsService customUserDetailsService;


    public JwtFilter(UserDetails userDetails, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.userDetails = userDetails;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer")){
            String jwt = authHeader.substring(7);
            String userName = jwtService.extractUserName(jwt);

            if(userName !=null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
            }

            if(jwtService.isTokenValid(jwt, (com.eventhub.eventhub_backend.model.User) userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, servletResponse);
    }

}

