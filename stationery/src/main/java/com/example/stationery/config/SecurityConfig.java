package com.example.stationery.config;

import com.example.stationery.authentication.CustomAccessDeniedHandler;
import com.example.stationery.authentication.JwtAuthenticationTokenFilter;
import com.example.stationery.authentication.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.beans.BeanProperty;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }

    protected void config(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers("/rest/**");
        http.authorizeHttpRequests().requestMatchers("/rest/login**").permitAll();
        http.securityMatcher("/rest/**").httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/rest/**").access("isAdmin('1') or isAdmin('0')")
                .requestMatchers(HttpMethod.POST, "/rest/**").access("isAdmin('1')")
                .requestMatchers(HttpMethod.DELETE, "/rest/**").access("isAdmin('1')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

    }
}
