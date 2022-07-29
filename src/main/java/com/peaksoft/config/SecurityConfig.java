package com.peaksoft.config;

import com.peaksoft.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/companies/**").hasAnyAuthority("ADMIN")
                .antMatchers("/teachers/**").hasAnyAuthority("ADMIN")
                .antMatchers("/courses").hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers("/courses/{id}/updateCourse").hasAnyAuthority("INSTRUCTOR", "ADMIN")
                .antMatchers("/courses/addCourse").hasAuthority("ADMIN")
                .antMatchers("/courses/delete/{id}").hasAuthority("ADMIN")
                .antMatchers("/groups").hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers("/groups/{id}/update").hasAnyAuthority("INSTRUCTOR", "ADMIN")
                .antMatchers("/groups/{id}").hasAnyAuthority("INSTRUCTOR", "ADMIN")
                .antMatchers("/groups/addGroup").hasAuthority("ADMIN")
                .antMatchers("/groups/{id}").hasAuthority("ADMIN")
                .antMatchers("/students").hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers("/students/{id}/update").hasAnyAuthority("INSTRUCTOR", "ADMIN")
                .antMatchers("/students/addStudent").hasAuthority("ADMIN")
                .antMatchers("/students/{id}").hasAuthority("ADMIN")
                .antMatchers("/students/search").hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll();
    }
}
