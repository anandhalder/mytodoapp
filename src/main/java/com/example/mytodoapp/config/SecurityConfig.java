package com.example.mytodoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	private final JwtAuthFilter jwtAuthFilter;

	public SecurityConfig(JwtAuthEntryPoint jwtAuthEntryPoint, JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthEntryPoint = jwtAuthEntryPoint;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
						.cors().disable()
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
						.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
						.and()
						.authorizeHttpRequests()
						.requestMatchers("/auth/**").permitAll()
						.anyRequest().authenticated()
						.and()
						.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


		return httpSecurity.build();
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
