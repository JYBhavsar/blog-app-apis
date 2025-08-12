package com.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath().startsWith("/api/user") || 
			   request.getServletPath().startsWith("/v3/api-docs") || 
			   request.getServletPath().startsWith("/swagger-ui") || 
			   request.getServletPath().startsWith("/v2/api-docs") || 
			   request.getServletPath().startsWith("/swagger-resources");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, IllegalArgumentException {

		String requestToken = request.getHeader("Authorization");
		String username = null;
		String token = null;

		if(shouldNotFilter(request)) {
			return;
		}
		
		if (requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			username = this.jwtTokenHelper.getUsernameFromToken(token);
		} else {
			System.out.println("JWT Token is either null or does not start with Bearer");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (this.jwtTokenHelper.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			} else {
				System.out.println("Invalid jwt Token!");
			}
		} else {
			System.out.println("Username is null or context is not null");
		}

		filterChain.doFilter(request, response);

	}

}
