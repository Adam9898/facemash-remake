package com.adam.facemash.config;

import com.adam.facemash.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final int BCRYPT_PASSWORD_STRENGTH = 10;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(@Qualifier("userServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCRYPT_PASSWORD_STRENGTH, new SecureRandom());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) {
        try {
            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/").not().authenticated()
                    .antMatchers("/registration").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/")
                    .defaultSuccessUrl("/app")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutSuccessUrl("/?logout")
                    .permitAll()
                    .and().httpBasic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
