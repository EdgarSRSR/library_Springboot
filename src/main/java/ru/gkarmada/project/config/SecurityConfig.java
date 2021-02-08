package ru.gkarmada.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
    auth
        .inMemoryAuthentication()
          .withUser("user")
          .password("{noop}user1234")
          .roles("USER")
        .and()
          .withUser("admin")
          .password("{noop}admin1234")
          .roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http
        .authorizeRequests()
          .antMatchers("/library/list")
          .permitAll().antMatchers("/genre/list")
          .hasRole("ADMIN")
          .anyRequest()
          .authenticated()
          .and()
        .formLogin()
          .and()
        .logout();
  }



}
