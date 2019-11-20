package br.com.codigojava.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.codigojava.library.service.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String ROLE_USER = "USER";
	private static final String ROLE_ADMIN = "ADMIN";

	@Bean
	public UserDetailsService myUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication()
//			.withUser("user").password(passwordEncoder().encode("user")).roles(ROLE_USER)
//			.and()
//			.withUser("admin").password(passwordEncoder().encode("admin")).roles(ROLE_ADMIN);		
		auth.userDetailsService(myUserDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/**").hasRole(ROLE_USER)
					.antMatchers(HttpMethod.PATCH, "/**").hasRole(ROLE_ADMIN)
					.antMatchers(HttpMethod.DELETE, "/**").hasRole(ROLE_ADMIN)
					.antMatchers(HttpMethod.GET, "/**").hasRole(ROLE_USER)
			.and()
				.csrf().disable();
	}

}