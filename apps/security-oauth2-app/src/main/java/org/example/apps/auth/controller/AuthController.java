package org.example.apps.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.auth.dto.SignUpRequest;
import org.example.apps.auth.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping({"/register","/sign-up"})
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){
        appUserService.createAppUser(signUpRequest.getUsername(), signUpRequest.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
