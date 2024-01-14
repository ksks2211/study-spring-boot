package org.example.apps.validation.controller;

import org.example.apps.validation.dto.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2023-12-22
 */
@RestController
@RequestMapping("/ex")
public class ExampleController {

    @GetMapping("")
    public Example example(){
        return Example.builder().notEmptyField("Not Empty").someField("Hello World").build();
    }
}
