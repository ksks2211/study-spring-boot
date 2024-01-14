package org.example.apps.auth.jwt;

import lombok.Builder;
import lombok.Data;
import org.example.apps.auth.auth.JwtUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;

/**
 * @author rival
 * @since 2023-12-21
 */
@Data
@Builder
public class JwtVerifyResult {
    private boolean verified;
    private boolean decoded;
    private String subject;
    private String provider;
    private String username;
    private Long id;
    private List<String> authorities;
    private Date expiryDate;

    public JwtUser toJwtUser(){
        List<SimpleGrantedAuthority> authorities = this.getAuthorities().stream().map(SimpleGrantedAuthority::new).toList();
        return JwtUser.builder()
            .id(this.getId())
            .username(this.getUsername())
            .provider(this.getProvider())
            .subject(this.getSubject())
            .authorities(authorities)
            .build();

    }
}
