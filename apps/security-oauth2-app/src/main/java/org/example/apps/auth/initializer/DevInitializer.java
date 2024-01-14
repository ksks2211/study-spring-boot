package org.example.apps.auth.initializer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.auth.service.AppUserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author rival
 * @since 2023-12-21
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DevInitializer implements ApplicationListener<ApplicationReadyEvent> {

//    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        appUserService.createAppUser("username","password");
        log.info("User Created");
    }
}
