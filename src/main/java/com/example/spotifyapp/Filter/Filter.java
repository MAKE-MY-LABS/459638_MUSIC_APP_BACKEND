package com.example.spotifyapp.Filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
public class Filter {

    // use jwt signing key from application.properties
    @Value("${jwt.signing.key}")
    private String jwtSigningKey;


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (request.getRequestURI().equals("/api/gaana/user/register")
                || request.getRequestURI().equals("/api/gaana/user/login") || request.getMethod().equals("OPTIONS") /**preflight request */ ) {
            filterChain.doFilter(request, response);
            return;
        }
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ") && requestTokenHeader.length() > 7) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                // try to get the subject from the token - username
                username = Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(jwtToken).getBody().getSubject();
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
            return;
        }
        System.out.println("Username: " + username + " Token: " + jwtToken);
        filterChain.doFilter(request, response);
    }

}
