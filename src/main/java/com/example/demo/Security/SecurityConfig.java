//package com.example.demo.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(configurer ->
//                configurer
//                        .requestMatchers( "/adopter/**").hasRole("ADOPTER")
//                        .requestMatchers( "/manager/**").hasRole("MANAGER")
//                        .requestMatchers( "/staff/**").hasRole("STAFF")
//                        .requestMatchers("/register/**").permitAll()
//                        .requestMatchers("/Login/**").permitAll()
//        )
//                        .httpBasic(Customizer.withDefaults())
//                        .csrf(AbstractHttpConfigurer::disable)
//                        .cors(Customizer.withDefaults());
//
//        http.httpBasic(httpBasicConfigurer -> httpBasicConfigurer.realmName("My Realm"));
//
//        return http.build();
//    }
//
//}