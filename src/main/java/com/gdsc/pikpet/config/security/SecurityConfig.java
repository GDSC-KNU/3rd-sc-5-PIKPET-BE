package com.gdsc.pikpet.config.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
//        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.authorizeHttpRequests(
                authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/loginPage/formLogin").permitAll()
                                .requestMatchers("/loginPage/loginFail").permitAll()
                                .requestMatchers("/loginPage/login").permitAll()
                                .requestMatchers("/api/**").permitAll() //구현의 편의를 위해 임시 개방
                                .requestMatchers("/login/**").permitAll()
                                .anyRequest().authenticated()
        );
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .formLogin(
                        formLogin ->
                                formLogin.loginPage("/loginPage/formLogin")
                                        .loginProcessingUrl("/login")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
                                        .successHandler(
                                                (request, response, authentication) -> {
                                                    response.setStatus(HttpServletResponse.SC_OK);
                                                    response.setHeader("Set-Cookie", "JSESSIONID=" + request.getSession().getId() + "; Path=/; HttpOnly; SameSite=None;");
                                                    response.getWriter().print("{\"success\": true, \"message\": \"Login successful.\", \"data\": {\"username\": \"" + authentication.getName() + "\"}}");
                                                    response.getWriter().flush();
                                                }
                                        )
                                        .failureHandler((request, response, exception) -> {
                                            System.out.println("exception = " + exception.getMessage());
                                            response.sendRedirect("/loginPage/loginFail");
                                        })
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        // 쿠키를 포함한 요청을 허용하기 위해 true로 설정
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
