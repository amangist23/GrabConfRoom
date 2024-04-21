package com.lowleveldesign.crms.Configs;

import com.lowleveldesign.crms.security.JwtAuthenticationEntryPoint;
import com.lowleveldesign.crms.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/actuator")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/v3/api-docs")).permitAll()
                            .requestMatchers("/auth/login").permitAll()
                            .requestMatchers("/api/crms/**").authenticated()
                            .anyRequest().authenticated())
                        .exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
