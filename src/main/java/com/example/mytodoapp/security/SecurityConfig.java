package com.example.mytodoapp.security;

import com.example.mytodoapp.config.JwtAuthEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final ApiAuthenticationProvider apiAuthenticationProvider;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;

	@Bean
	public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable()
						.cors().disable()
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
						.authorizeHttpRequests()
						.requestMatchers("/api/**").authenticated()
						.requestMatchers("/gui/**").authenticated()
						.anyRequest().authenticated();

		return httpSecurity.build();
	}

	@Bean
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// For API Authentication
		auth.authenticationProvider(apiAuthenticationProvider);
		// For GUI Authentication
		// TODO: ADD GUI AUTHENTICATION
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
