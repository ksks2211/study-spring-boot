package org.example.apps.validation.controller;

import jakarta.validation.Valid;
import org.example.apps.validation.dto.req.UserRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2023-10-24
 */

@RestController
@RequestMapping("valid")
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationRequest user){
        return ResponseEntity.ok(user);
    }
}
