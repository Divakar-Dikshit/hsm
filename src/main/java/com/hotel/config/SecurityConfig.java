package com.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import static org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable;

@Configuration
public class SecurityConfig {

    private final JWTFilter filter;

    public SecurityConfig(JWTFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();  //h(cd)2
        http.addFilterBefore(filter, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll(); //haap

        //this all code only mentioned url anybody can access other url need to authenticate
//        http.authorizeHttpRequests().requestMatchers("/api/v1/users/login",
//                        "/api/v1/users/signup",
//                        "/api/v1/users/signup-property-owner")
//                .permitAll().requestMatchers("/api/v1/country/addCountry").hasAnyRole("OWNER","ADMIN").
//                anyRequest().authenticated();
            return http.build();
}

}
