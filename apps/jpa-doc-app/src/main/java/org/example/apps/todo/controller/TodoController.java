package org.example.apps.todo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.apps.todo.dto.TodoForm;
import org.example.apps.todo.dto.TodoInfoDto;
import org.example.apps.todo.dto.TodoListDto;
import org.example.apps.todo.service.TodoService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rival
 * @since 2023-10-19
 */




@RestController
@RequestMapping("todos")
@RequiredArgsConstructor
@Tag(name="Todo Management", description = "APIs for managing todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("")
    public TodoListDto todo(){
        List<TodoInfoDto> dtoList = todoService.findAll();
        TodoListDto todoListDto = new TodoListDto();
        todoListDto.setTodos(dtoList);
        return todoListDto;
    }

    @PostMapping("")
    public void create(@RequestBody TodoForm form){
        todoService.createTodo(form);
    }
}
