//package com.smspai.ionic.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//      http
//          .authorizeRequests()
//            //static resources configuration
//            .antMatchers("/resources/**", "/webjars/**", "/img/**").permitAll()
//            //login page and registration end-point
//            .antMatchers("/login", "/registration").permitAll()
//            //all other requests
//            .anyRequest().authenticated()
//            .and()
//          // login form configuration
//          .formLogin()
//            .loginPage("/login")
//            .failureUrl("/login?error")
//            .defaultSuccessUrl("/")
//            .permitAll()
//            .and()
//          //logout configuration
//          .logout()
//            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//            .logoutSuccessUrl("/login");
//  }
//
//}
