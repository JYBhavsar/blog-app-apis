package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blog.security.CustomUserDetailService;
import com.blog.security.JwtAuthenticationEntryPoint;
import com.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity
public class SecurityConfig{

	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint  entryPoint;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.csrf(csrf -> csrf.disable())
            
        	.authorizeHttpRequests((auth) -> auth
        			.requestMatchers("/api/v1/auth/**").permitAll()
        			.requestMatchers(HttpMethod.GET,
        					"/v2/**",
        					"/v3/**",
        					"/swagger-ui/**",
        					"/swagger-resources/**",
        					"/webjars/**").permitAll()
        			.anyRequest().authenticated()
        	)
            
            .exceptionHandling(exceptionHanding -> exceptionHanding.authenticationEntryPoint(this.entryPoint))

            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            .addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class)
            
            .httpBasic(Customizer.withDefaults());
            
        return http.build();
    }
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	
	
}
