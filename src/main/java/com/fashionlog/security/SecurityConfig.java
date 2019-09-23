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
      
      security.csrf().disable();
      
      security.authorizeRequests().antMatchers("/login").permitAll();
      security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
      security.authorizeRequests().antMatchers("/**").hasAnyRole("USER");
      
      security.formLogin().loginPage("/login").defaultSuccessUrl("/myFeed", true);

      security.formLogin().loginPage("/login").failureUrl("/login?fail");
      security.formLogin().usernameParameter("id");
      security.exceptionHandling().accessDeniedPage("/accessDenied");
      security.logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/login");
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }
}

            