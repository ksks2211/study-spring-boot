package org.example.apps.auth.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author rival
 * @since 2023-12-20
 */
@Getter
public class AuthUser extends User {
    private final Long id;
    private final String provider;
    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, String provider) {
        super(username, password, authorities);
        this.id = id;
        this.provider=provider;
    }
}
