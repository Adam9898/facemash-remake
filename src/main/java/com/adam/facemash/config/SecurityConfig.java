package com.adam.facemash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) {

    }

    public void configure(HttpSecurity httpSecurity) {
        try {
            httpSecurity
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/")
                    .permitAll()
                    .antMatchers("/app").hasRole("USER")
                    .antMatchers("/top").hasRole("USER")
                    .antMatchers("/registration").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/")
                    .permitAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
