package com.example.demo.config;

//import com.example.demo.entity.Role.ADMIN;
import com.example.demo.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.demo.entity.Role.ADMIN;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
        private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
        "/getAllGroup"
        };
    private final JwtAuthentificationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
                http.csrf( AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                    req.requestMatchers(WHITE_LIST_URL)
                        .permitAll()
                        .requestMatchers("/getAllStudent").hasAuthority(ADMIN.name())
                            .anyRequest()
                            .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



////        http.csrf()
//            .disable()
//            .authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/**")
//                .permitAll()
//                .requestMatchers(GET,"/getAllGroup").hasRole(Role.ADMIN.name())
//                 //   /api/v1/auth/**
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
