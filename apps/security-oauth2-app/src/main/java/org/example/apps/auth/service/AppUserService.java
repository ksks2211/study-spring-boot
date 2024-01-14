package org.example.apps.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.auth.auth.AuthUser;
import org.example.apps.auth.auth.JwtUser;
import org.example.apps.auth.entity.AppRole;
import org.example.apps.auth.entity.AppUser;
import org.example.apps.auth.entity.AuthProvider;
import org.example.apps.auth.repository.AppUserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rival
 * @since 2023-12-20
 */
@RequiredArgsConstructor
@Slf4j
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;



    // static methods for mapping
    public static AuthUser fromEntity(AppUser entity) {
        AppRole role = entity.getRole();
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));

        return new AuthUser(
            entity.getUsername(),
            entity.getPassword(),
            authorities,
            entity.getId(),
            entity.getProvider().name()
        );
    }
    public static AppUser toEntity(String username, String password){
        return AppUser.builder().username(username).password(password).build();
    }

    public static AppUser toEntity(AuthProvider provider, String providerId){
        return AppUser.builder().provider(provider).providerId(providerId).username(provider.name()+"_"+providerId).build();
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username : " + username + " is not found"));
        if(!appUser.getProvider().equals(AuthProvider.LOCAL))
            throw new UsernameNotFoundException("Username : " + username + " is not found");
        return fromEntity(appUser);
    }


    @Transactional
    public void createAppUser(String username, String password){
        if(appUserRepository.existsByUsername(username)){
            throw new DataIntegrityViolationException("Username : "+username+" is already taken");
        }
        AppUser user = toEntity(username, passwordEncoder.encode(password));
        appUserRepository.save(user);
    }

    @Transactional
    public JwtUser createOAuth2AppUserIfNotExists(AuthProvider provider, String sub, String username){
        AppUser user = appUserRepository.findByProviderAndProviderId(provider, sub).orElseGet(() -> toEntity(provider, sub));
        appUserRepository.save(user);
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        return JwtUser.builder()
            .id(user.getId())
            .username(username)
            .provider(user.getProvider().name())
            .authorities(authorities)
            .subject(user.getId().toString())
            .build();
    }


}
