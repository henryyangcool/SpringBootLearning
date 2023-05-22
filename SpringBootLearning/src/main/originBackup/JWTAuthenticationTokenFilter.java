package com.develop.Service.JWT;

import com.develop.Entity.Role.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

//@Component
//public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JWTService jwtTokenService;
//    @Autowired
//    private UserDetailsService userDetailsService;

    //    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String token = getToken(request);
//        if (StringUtils.isNotBlank(token) && jwtTokenService.validateToken(token)) {
//            String username = jwtTokenService.getUsernameFromToken(token);
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            if (userDetails != null) {
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//        chain.doFilter(request, response);
//    }
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader(AUTHORIZATION);
//        if (authHeader != null) {
//            String accessToken = authHeader.replace("Bearer ", "");
//
//            Map<String, Object> claims = jwtTokenService.parseToken(accessToken);
//
//            String username = (String)claims.get("username");
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
////                    new UsernamePasswordAuthenticationToken(claims.get("username"), claims.get("password"));
//                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        }
//
//        filterChain.doFilter(request, response);
//    }

//    private String getToken(HttpServletRequest request) {
//        String header = request.getHeader(AUTHORIZATION);
//        if (StringUtils.isNotBlank(header) && header.startsWith("Bearer ")) {
//            return header.substring(7);
//        }
//        return null;
//    }
//}

