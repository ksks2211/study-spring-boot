package org.example.apps.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.apps.validation.dto.req.TodoForm;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String addTodo(@Validated @ModelAttribute("todoForm")TodoForm form, BindingResult br){
        log.info("Form : {}",form);
        log.info("BindingResult : {}",br);

        if(br.hasErrors()){
            return "Error";
        }
        return "Success";
    }
}
