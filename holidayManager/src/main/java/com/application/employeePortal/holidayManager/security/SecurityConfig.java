package com.application.employeePortal.holidayManager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
        .withUser("dev").password(encoder().encode("dev")).roles("USER","ADMIN")
        .and()
        .withUser("user").password(encoder().encode("user")).roles("USER")
        .and()
        .withUser("sa").password(encoder().encode("sa")).roles("USER", "ADMIN");

    }

    @Bean
    public PasswordEncoder  encoder() {
            return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/leaves/*").authenticated()
                .antMatchers(HttpMethod.POST, "/leaves/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/leaves/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/leaves/**").hasRole("ADMIN")
                .and().headers().frameOptions().disable().and()
                .csrf().disable()
                .formLogin().disable();
    }


}