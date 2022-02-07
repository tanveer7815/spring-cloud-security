package com.roytuts.spring.boot.auth.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService getUserDetailsServivce() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsServivce());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		return daoAuthenticationProvider;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		

//		http.authorizeRequests().antMatchers("/echo/**").hasRole("Admin").antMatchers("/alert/**").hasRole("user")
//				.antMatchers("/**").permitAll().and().csrf().disable();
		http.authorizeRequests().antMatchers("/auth/login", "/auth/register").permitAll();
		http.authorizeRequests().antMatchers("/echo/**").hasAuthority("Admin").antMatchers("/alert/**")
		.hasAuthority("User").antMatchers(org.springframework.http.HttpHeaders.ALLOW).permitAll().anyRequest().authenticated()
		.and().csrf().disable();

//		http.cors().disable();
//		http.csrf().disable();
//
//		http.authorizeRequests().antMatchers("/register","/login").permitAll();
//			http.authorizeRequests().antMatchers("/echo/**").hasAuthority("Admin")
//				.antMatchers(org.springframework.http.HttpHeaders.ALLOW).permitAll().anyRequest().authenticated();

	}

}
