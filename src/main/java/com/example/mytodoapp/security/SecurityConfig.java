package com.example.mytodoapp.security;

import com.example.mytodoapp.config.JwtAuthEntryPoint;
import com.example.mytodoapp.config.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final ApiAuthenticationProvider apiAuthenticationProvider;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	private final JwtAuthFilter jwtAuthFilter;
	private final UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable()
						.cors().disable()
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
						.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
						.and()
						.authorizeHttpRequests()
						.requestMatchers("/api/**").authenticated()
						.requestMatchers("/gui/**").authenticated()
						//.requestMatchers("/auth/**").permitAll() TODO: Not Required for now, because for API application user will
						// TODO: send username and password for every call !
						.anyRequest().authenticated();

		// TODO: Removed the JwtAuthFilter for now, will implement for GUI later.
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
