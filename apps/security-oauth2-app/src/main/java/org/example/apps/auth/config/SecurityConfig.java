package org.example.apps.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.auth.dto.ForbiddenAccessResponse;
import org.example.apps.auth.dto.UnauthorizedAccessResponse;
import org.example.apps.auth.filter.JwtAuthenticationFilter;
import org.example.apps.auth.filter.JwtLogInFilter;
import org.example.apps.auth.handler.AuthFailureHandler;
import org.example.apps.auth.handler.AuthSuccessHandler;
import org.example.apps.auth.jwt.JwtManager;
import org.example.apps.auth.repository.AppUserRepository;
import org.example.apps.auth.service.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * @author rival
 * @since 2023-12-21
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final AppUserRepository appUserRepository;
    private final AuthenticationConfiguration authConfig;
    private final JwtManager jwtManager;
    private final ObjectMapper objectMapper;

    // CORS
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));
        // configuration.setAllowCredentials(true);

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(true);
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AppUserService appUserService() {
        return new AppUserService(appUserRepository, passwordEncoder());
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthFailureHandler authFailureHandler(){
        return  new AuthFailureHandler(objectMapper);
    }


    @Bean
    public AuthSuccessHandler authSuccessHandler(){
        return new AuthSuccessHandler(appUserService(), jwtManager, objectMapper);
    }
    @Bean
    public JwtLogInFilter jwtLogInFilter() throws Exception {
        JwtLogInFilter jwtLogInFilter = new JwtLogInFilter("/auth/log-in", authenticationManager(), objectMapper);
        jwtLogInFilter.setAuthenticationFailureHandler(authFailureHandler());
        jwtLogInFilter.setAuthenticationSuccessHandler(authSuccessHandler());
        return jwtLogInFilter;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtManager);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(
                sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .httpBasic(Customizer.withDefaults())
            .addFilterAt(jwtAuthenticationFilter(), BasicAuthenticationFilter.class)
            .addFilterAt(jwtLogInFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers(
                        new AntPathRequestMatcher("/auth/**"),
                        new AntPathRequestMatcher("/oauth2/authorization/google"),
                        new AntPathRequestMatcher("/login/oauth2/code/google"))
                    .permitAll()
                    .anyRequest().authenticated())
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    UnauthorizedAccessResponse body = new UnauthorizedAccessResponse();
                    log.debug("Unauthorized Exception : {}", authException.getMessage());

                    response.getWriter().write(objectMapper.writeValueAsString(body));
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    ForbiddenAccessResponse body = new ForbiddenAccessResponse();
                    log.debug("Access Denied Exception : {}", accessDeniedException.getMessage());

                    response.getWriter().write(objectMapper.writeValueAsString(body));
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                }))
            .oauth2Login(oauth2Login-> oauth2Login
                .successHandler(authSuccessHandler())
                .failureHandler(authFailureHandler())

            )


        ;

        return http.build();

    }

}
