package org.example.apps.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author rival
 * @since 2023-12-20
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="app_users",uniqueConstraints = {@UniqueConstraint(columnNames = {"provider","providerId"})})
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AppRole role = AppRole.USER;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AuthProvider provider = AuthProvider.LOCAL;

    // OAuth2
    private String providerId;


    public boolean isLocalUser() {
        return AuthProvider.LOCAL.equals(this.provider);
    }
}
