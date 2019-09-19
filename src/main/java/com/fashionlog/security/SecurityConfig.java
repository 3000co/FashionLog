package com.fashionlog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

   @Autowired
   private SecurityUserDetailsService userDetailsService;

   @Override
   protected void configure(HttpSecurity security) throws Exception {

      security.userDetailsService(userDetailsService);

      security.authorizeRequests().antMatchers("/").permitAll();
      security.authorizeRequests().antMatchers("/user/**").hasAnyRole("USER");
      security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

      security.csrf().disable();



      security.formLogin().loginPage("/login").defaultSuccessUrl("/feed", true);
      security.formLogin().loginPage("/login").failureUrl("/loginFail");
      security.formLogin().usernameParameter("id");
      security.exceptionHandling().accessDeniedPage("/accessDenied");
      security.logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/login");

   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }
}

            