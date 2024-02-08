package org.example.apps.todo.service;

import org.example.apps.todo.dto.TodoForm;
import org.example.apps.todo.dto.TodoInfoDto;
import org.example.apps.todo.entity.Todo;

import java.util.List;

/**
 * @author rival
 * @since 2023-10-19
 */

public interface TodoService {
    void createTodo(TodoForm form);

    List<TodoInfoDto> findAll();
}
