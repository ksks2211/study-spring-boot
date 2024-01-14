package org.example.apps.auth.repository;

import org.example.apps.auth.entity.AppUser;
import org.example.apps.auth.entity.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author rival
 * @since 2023-12-20
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<AppUser> findByProviderAndProviderId(AuthProvider provider, String providerId);
}
