////package com.url.shortener.security.config;//package com.url.shortener.security.config;
//////
//////import com.url.shortener.security.jwt.JwtAuthenticationFilter;
//////import com.url.shortener.service.UserDetailsServiceImpl;
//////import lombok.AllArgsConstructor;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.context.annotation.Configuration;
//////import org.springframework.http.HttpMethod;
//////import org.springframework.security.authentication.AuthenticationManager;
//////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//////import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//////import org.springframework.security.crypto.password.PasswordEncoder;
//////import org.springframework.security.web.SecurityFilterChain;
//////import org.springframework.security.web.authentication.AuthenticationConverter;
//////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//////
//////@Configuration
//////@EnableWebSecurity
//////@EnableMethodSecurity
//////@AllArgsConstructor
//////public class WebSecurityConfig {
//////
//////    private final UserDetailsServiceImpl userDetailsService;
//////
//////    @Bean
//////    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//////        return new JwtAuthenticationFilter();
//////    }
//////
//////    @Bean
//////    public PasswordEncoder passwordEncoder(){
//////        return new BCryptPasswordEncoder();
//////    }
//////
//////    @Bean
//////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//////        return authenticationConfiguration.getAuthenticationManager();
//////    }
//////    @Bean
//////    public DaoAuthenticationProvider authenticationProvider(){
//////        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//////        authProvider.setUserDetailsService(userDetailsService);
//////        authProvider.setPasswordEncoder(passwordEncoder());
//////        return authProvider;
//////    }
//////
//////    // http://domain.com/xyz  => google.com
//////    @Bean
//////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
////
////
////////        http.cors(cors ->{})
//////        http.cors(); // just this line
//////        .csrf(csrf -> csrf.disable())
//////                .authorizeHttpRequests(auth -> auth
//////                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//////                        .requestMatchers("/api/auth/**").permitAll()
//////                        .requestMatchers("/api/urls/**").authenticated()
//////                        .requestMatchers("/{shortUrl}/**").permitAll()
//////                        .anyRequest().authenticated()
//////                );
//////
//////        http.addFilterBefore(jwtAuthenticationFilter(),
//////                UsernamePasswordAuthenticationFilter.class);
//////
//////        return http.build();
//////    }
////
////import com.url.shortener.security.jwt.JwtAuthenticationFilter;
////import com.url.shortener.service.UserDetailsServiceImpl;
////import lombok.AllArgsConstructor;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.http.HttpMethod;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.Customizer;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.web.cors.CorsConfigurationSource;
////
////@Configuration
////@AllArgsConstructor
////@EnableMethodSecurity
////public class WebSecurityConfig {
////
////    private final UserDetailsServiceImpl userDetailsService;
////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////    private final CorsConfigurationSource corsConfigurationSource;
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
////        return config.getAuthenticationManager();
////    }
////
////    @Bean
////    public DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////        provider.setUserDetailsService(userDetailsService);
////        provider.setPasswordEncoder(passwordEncoder());
////        return provider;
////    }
////
//////    @Bean
//////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//////        http
//////                .cors(cors -> cors.configurationSource(corsConfigurationSource)) // new way in 6.1+
//////                .csrf(csrf -> csrf.disable())
//////                .authorizeHttpRequests(auth -> auth
//////                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//////                        .requestMatchers("/api/auth/**").permitAll()
//////                        .requestMatchers("/api/urls/**").authenticated()
//////                        .requestMatchers("/{shortUrl}/**").permitAll()
//////                        .anyRequest().authenticated()
//////                )
//////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//////
//////        return http.build();
//////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
////                .cors(Customizer.withDefaults()) // Use your WebConfig CORS
////                .authorizeHttpRequests(auth -> auth
////                        // This is the line you are likely missing:
////                        .requestMatchers("/api/auth/public/**").permitAll()
////                        .requestMatchers("/{shortUrl}").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////        // Add your JWT filter
////        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
////}
//
//
//
//package com.url.shortener.security.config;
//
//import com.url.shortener.security.jwt.JwtAuthenticationFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor // Using this instead of AllArgsConstructor to avoid circular dependency issues
//public class WebSecurityConfig {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                // This pulls the configuration from your WebConfig.java
//                .cors(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        // Make sure this matches your Controller path exactly
////                        .requestMatchers("/api/auth/public/**").permitAll()
////                        .requestMatchers("/{shortUrl}").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/auth/public/**").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/{shortUrl}").permitAll()
//                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                        .anyRequest().authenticated()
//                );
//
//
//        // Add your JWT filter
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//}
//


package com.url.shortener.security.config;

import com.url.shortener.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Complete disable
                .cors(Customizer.withDefaults()) // Link to WebConfig
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Allow EVERYTHING under /api/auth/ without exception
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/{shortUrl}").permitAll()
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
