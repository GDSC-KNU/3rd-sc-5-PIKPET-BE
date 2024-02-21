package com.gdsc.pikpet.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        http.authorizeHttpRequests(
                authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/", "/swagger-ui.html").permitAll()
                                .requestMatchers("/loginPage/formLogin").permitAll()
                                .requestMatchers("/loginPage/loginFail").permitAll()
                                .requestMatchers("/api/**").permitAll() //구현의 편의를 위해 임시 개방
                                .requestMatchers("/login/**").authenticated()
                                .anyRequest().authenticated()
        );

        http.csrf(cr -> cr.disable());

        http.formLogin(
                formLogin ->
                        formLogin.loginPage("/loginPage/formLogin")
                                .loginProcessingUrl("/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/loginPage/loginSuccess",true)// user 같이 정보 반환, 프런트 연결시 변경 필요
                                .failureHandler((request, response, exception) -> {
                                    System.out.println("exception = " + exception.getMessage());
                                    response.sendRedirect("/loginPage/loginFail");
                                })
        );
http.cors();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
