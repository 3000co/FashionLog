package com.fashionlog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BoardUserDetailsService boardUserDetailsService;

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.authorizeRequests().antMatchers("/").permitAll();

		security.csrf().disable();
		security.formLogin().loginPage("/login").defaultSuccessUrl("/", true);
		security.exceptionHandling().accessDeniedPage("/login");
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/");

		security.userDetailsService(boardUserDetailsService);

	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
