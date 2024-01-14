package org.example.apps.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.auth.auth.AuthUser;
import org.example.apps.auth.auth.JwtUser;
import org.example.apps.auth.dto.LogInSuccessResponse;
import org.example.apps.auth.entity.AuthProvider;
import org.example.apps.auth.jwt.JwtManager;
import org.example.apps.auth.service.AppUserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

import static org.example.apps.auth.mapper.UserMapper.fromAuthUserToJwtUser;

/**
 * @author rival
 * @since 2023-12-24
 */

@RequiredArgsConstructor
@Slf4j
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final AppUserService appUserService;
    private final JwtManager jwtManager;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        Object principal = authentication.getPrincipal();

        JwtUser jwtUser = (principal instanceof OidcUser oidcUser) ? genJwtUserFromOidcUser(oidcUser) : fromAuthUserToJwtUser((AuthUser)principal);

        String token = jwtManager.createToken(jwtUser);
        LogInSuccessResponse body = LogInSuccessResponse.builder()
            .token(token)
            .username(jwtUser.getUsername())
            .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(body));
    }

    private JwtUser genJwtUserFromOidcUser(OidcUser oidcUser) {

        AuthProvider provider = AuthProvider.GOOGLE;
        String sub = oidcUser.getAttribute("sub");
        String name = oidcUser.getAttribute("name");

        return appUserService.createOAuth2AppUserIfNotExists(provider, sub, name);
    }
}
