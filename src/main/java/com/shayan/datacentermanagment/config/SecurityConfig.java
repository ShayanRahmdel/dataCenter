package com.shayan.datacentermanagment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a->a
                        .requestMatchers("/customer/signup").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/customer/changePassword").hasRole("CUSTOMER")
                        .requestMatchers("/equipment/**").hasRole("CUSTOMER")
                        .requestMatchers("/service/buyService").hasRole("CUSTOMER")
                        .requestMatchers("/ticket/createTicket").hasRole("CUSTOMER")
                        .requestMatchers("/ticket").hasRole("CUSTOMER")
                        .requestMatchers("/ticketReply/createReply").hasRole("SUPPORT")
                        .requestMatchers("/ticketReply/seeReplyByUserId").hasAnyRole("CUSTOMER", "SUPPORT")
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
