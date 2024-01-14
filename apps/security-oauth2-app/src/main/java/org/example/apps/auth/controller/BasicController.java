package org.example.apps.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2023-12-21
 */
@RestController
@RequestMapping("")
public class BasicController {

    @GetMapping("")
    public String basic(){
        return "Hello World";
    }
}
