package org.example.apps.auth.mapper;

import org.example.apps.auth.auth.AuthUser;
import org.example.apps.auth.auth.JwtUser;
import org.springframework.stereotype.Component;

/**
 * @author rival
 * @since 2024-01-01
 */
@Component
public class UserMapper {
    public static JwtUser fromAuthUserToJwtUser(AuthUser authUser){
        return JwtUser.builder()
            .id(authUser.getId())
            .subject(authUser.getId().toString())
            .username(authUser.getUsername())
            .authorities(authUser.getAuthorities())
            .provider(authUser.getProvider())
            .build();
    }
}
