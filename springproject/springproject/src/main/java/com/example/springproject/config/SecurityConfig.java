package com.example.springproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 關閉 CSRF，方便 Postman 測試
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/register", "/api/login").permitAll()  // 允許訪問 register & login
                .anyRequest().authenticated()                 // 其他路徑都需要登入
            )
            .formLogin(form -> form.disable())
            .httpBasic(Customizer.withDefaults()); // 暫時開啟基本驗證（開發用）

        return http.build();
    }
}