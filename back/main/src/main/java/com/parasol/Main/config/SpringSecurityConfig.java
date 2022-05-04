package com.parasol.Main.config;

import com.parasol.Main.security.filter.ApiKeyAuthFilter;
import com.parasol.Main.security.filter.ApiKeyAuthManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final ApiKeyAuthFilter filter;

    // 필터1
    @Order(0)
    @Configuration
    class Filter1 extends WebSecurityConfigurerAdapter {
        @Value("${authentication.account-key}")
        private String accountKey;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            filter.setAuthenticationManager(new ApiKeyAuthManager(accountKey));
            http
                    .antMatcher("/account/**")
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }
    }


    // 필터2
    @Order(1)
    @Configuration
    class Filter2 extends WebSecurityConfigurerAdapter {
        @Value("${authentication.client-key}")
        private String clientKey;

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            //Todo: CORS 해결하기 추가, 클라이언트 아이디도 받을 것이기 때문에
            //Todo : CORS는 하드코딩 부터 구현 해보기
            //Todo : 하드코딩으로 잘 되면 -> application.properties로 관리하기

            filter.setAuthenticationManager(new ApiKeyAuthManager(clientKey));
            http
                    .antMatcher("/client/**")
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }
    }

    // 필터3
    @Order(2)
    @Configuration
    class Filter3 extends WebSecurityConfigurerAdapter {
        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("*"));
            config.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "PATCH", "DELETE"));
            config.setAllowedHeaders(Arrays.asList("*"));
            config.setAllowCredentials(false);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config);

            return source;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

}