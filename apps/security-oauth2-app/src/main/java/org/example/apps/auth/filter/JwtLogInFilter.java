package org.example.apps.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.auth.dto.LogInRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author rival
 * @since 2023-12-21
 */
@Slf4j
public class JwtLogInFilter extends AbstractAuthenticationProcessingFilter {


    private final ObjectMapper objectMapper;


    public JwtLogInFilter(String filterProcessesUrl, AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher(filterProcessesUrl, "POST"), authenticationManager);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        try (InputStream inputStream = request.getInputStream()) {
            LogInRequest logInRequest = objectMapper.readValue(inputStream, LogInRequest.class);
            String principal = logInRequest.getUsername();
            String credentials = logInRequest.getPassword();
            if (!StringUtils.hasText(principal) || !StringUtils.hasText(credentials)) {
                throw new UsernameNotFoundException(principal);
            }
            // authenticated = false
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
            return getAuthenticationManager().authenticate(authRequest);
        }
    }



}
