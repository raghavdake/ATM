package com.atm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class Securityconfig extends WebSecurityConfigurerAdapter {

	// Securing the urls and allowing role-based access to these urls.

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/showBalance").hasAnyRole("USER").and()
				.authorizeRequests().antMatchers("/api/withdraw/**").hasAnyRole("USER").and().formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
		managerBuilder.inMemoryAuthentication().withUser("123456789").password(passwordEncoder().encode("1234"))
				.roles("USER").and().withUser("987654321").password(passwordEncoder().encode("4321")).roles("USER");
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

}