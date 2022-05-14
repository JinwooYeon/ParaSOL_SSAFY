package com.parasol.main_authentication.config;

import com.parasol.main_authentication.security.filter.*;
import com.parasol.main_authentication.service.AccountAuthenticateService;
import com.parasol.main_authentication.service.ClientAuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
    @Autowired
    private AccountAuthenticateService accountAuthenticateService;
    @Autowired
    private ClientAuthenticateService clientAuthenticateService;
    @Autowired
    private final AccountApiKeyAuthFilter accountApiKeyAuthFilter;
    @Autowired
    private final ClientApiKeyAuthFilter clientApiKeyAuthFilter;
    @Autowired
    private HttpServletRequest request;

    // 필터1
    @Order(0)
    @Configuration
    class Filter1 extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            accountApiKeyAuthFilter.setAuthenticationManager(new AccountApiKeyAuthManager(accountAuthenticateService, request));
            http
                    .antMatcher("/account/**")
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(accountApiKeyAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }
    }


    // 필터2
    @Order(1)
    @Configuration
    class Filter2 extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            //Todo: CORS 해결하기 추가, 클라이언트 아이디도 받을 것이기 때문에
            //Todo : CORS는 하드코딩 부터 구현 해보기
            //Todo : 하드코딩으로 잘 되면 -> application.properties로 관리하기

            clientApiKeyAuthFilter.setAuthenticationManager(new ClientApiKeyAuthManager(clientAuthenticateService, request));
            http
                    .antMatcher("/client/**")
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(clientApiKeyAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }
    }

    // 필터3
    @Order(2)
    @Configuration
    class Filter3 extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .cors()
                    .and()
                    .authorizeRequests()
                    .anyRequest().permitAll();
        }

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