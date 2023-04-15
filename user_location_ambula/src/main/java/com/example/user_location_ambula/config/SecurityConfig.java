package com.example.user_location_ambula.config;

import com.example.user_location_ambula.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    * Spring Security
    * All security measures happen here
    *
    * SecurityConfig extends in-built WebSecurityConfigurerAdapter and
    * Overrides configure(HttpSecurity http) method that helps to match the endpoint paths and their authority checks
    *
    * We allow the root path "/" to be publicly available
    * Other endpoints are under authority checks
    * create_data, update_data - ADMIN
    * get_users/N - READER Only
    */

    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/user_data/get_users/**").hasAuthority("READER")
                .antMatchers(HttpMethod.POST, "/user_data/create_data").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/user_data/update_data/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic().and().csrf().disable();
    }
}
