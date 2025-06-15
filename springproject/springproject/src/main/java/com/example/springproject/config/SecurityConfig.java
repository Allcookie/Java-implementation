package com.example.springproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
// import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 關閉 CSRF，方便 Postman 測試
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/register", "/api/login").permitAll()  // 允許訪問 register & login
                .requestMatchers(HttpMethod.GET, "/api/posts").permitAll()  // 可將 permitAll() 改為 authenticated() 只讓登入者查詢
                .anyRequest().authenticated()                 // 其他路徑都需要登入
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
            // .formLogin(form -> form.disable())
            // .httpBasic(Customizer.withDefaults()); // 暫時開啟基本驗證（開發用）

        return http.build();
    }
}