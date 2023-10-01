package com.example.mytodoapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final ApiAuthenticationProvider apiAuthenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable()
						.cors().disable()
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and().authorizeHttpRequests().requestMatchers("/auth/**").permitAll()
						.and().authorizeHttpRequests().anyRequest().authenticated()
						.and().httpBasic();

		return httpSecurity.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(apiAuthenticationProvider);
		return authenticationManagerBuilder.build();
	}
}
