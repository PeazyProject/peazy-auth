package com.peazy.auth.configuration;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui/**", "/webjars/**","/swagger-ui/**");
		// web.ignoring().antMatchers( "/**/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Enable CORS and disable CSRF
		// http = http.cors().and().csrf().disable();
		http = http.csrf().disable();

		// Set session management to stateless
		http = http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and();

		// Set unauthorized requests exception handler
		http = http
				.exceptionHandling()
				.authenticationEntryPoint(
						(request, response, exception) -> {
							response.sendError(
									HttpServletResponse.SC_UNAUTHORIZED,
									exception.getMessage());
						})
				.and();

		// Set permissions on endpoints
		http.authorizeRequests()
				// Our public endpoints
				.antMatchers("/authentication", "/user/createCustomerUser").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				// .antMatchers(HttpMethod.GET, "/api/**").permitAll()
				// .antMatchers(HttpMethod.POST, "/api/**").permitAll()

				// Our private endpoints
				.anyRequest().authenticated();
				// .and().httpBasic().and().cors().and().
				// exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				// .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add JWT token filter
		http.addFilterBefore(
				jwtRequestFilter,
				UsernamePasswordAuthenticationFilter.class);

	}
}
