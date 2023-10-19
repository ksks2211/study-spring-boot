package org.example.apps.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2023-10-19
 */

@RestController
@RequestMapping("todos")
public class TodoController {


    @GetMapping("")
    public String todo(){
        return "Hello Todo";
    }
}
