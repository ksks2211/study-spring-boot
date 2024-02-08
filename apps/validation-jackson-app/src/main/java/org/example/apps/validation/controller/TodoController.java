package org.example.apps.validation.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.validation.dto.req.TodoForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2023-11-08
 */

@RestController
@RequestMapping("todo")
@Slf4j
public class TodoController {

    @PostMapping("")
    public String addTodo(@Valid @RequestBody  TodoForm form){


        return "Success";
    }
}
