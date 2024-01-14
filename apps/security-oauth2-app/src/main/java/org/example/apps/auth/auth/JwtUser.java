package org.example.apps.auth.auth;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author rival
 * @since 2023-12-26
 */
@Data
@Builder
public class JwtUser {

    private final Long id;
    private final String username;
    private final String provider;
    private final String subject;
    @Builder.Default
    private final Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

}
