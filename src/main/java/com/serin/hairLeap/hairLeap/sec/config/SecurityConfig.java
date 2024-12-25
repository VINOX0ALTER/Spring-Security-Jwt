package com.serin.hairLeap.hairLeap.sec.config;
import com.serin.hairLeap.hairLeap.sec.entities.AppUser;
import com.serin.hairLeap.hairLeap.sec.filters.JwtAuthentificationFilter;
import com.serin.hairLeap.hairLeap.sec.filters.JwtAuthorizationFilter;
import com.serin.hairLeap.hairLeap.sec.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AccountService accountService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   final AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        http
        .headers(header -> header.frameOptions(
                HeadersConfigurer.FrameOptionsConfig::disable
        ))
        .sessionManagement(
                sss->sss.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )
        )
        .addFilter(new JwtAuthentificationFilter(authenticationConfiguration.getAuthenticationManager()))
        .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(
                aut -> aut.requestMatchers("/h2-console/**","/refreshToken/**").permitAll()
        )
        //.authorizeHttpRequests(aut -> aut.requestMatchers(HttpMethod.GET,"/users/**").hasAuthority("ADMIN"))
        //.authorizeHttpRequests(aut -> aut.requestMatchers(HttpMethod.POST,"/users/**").hasAuthority("USER"))
        //.formLogin(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        return http.build();
    }





}


