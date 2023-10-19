package org.example.apps.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.apps.todo.entity.Todo;
import org.example.apps.todo.repository.TodoRepository;

/**
 * @author rival
 * @since 2023-10-19
 */


@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    @Override
    public void createTodo(String content) {
        Todo todo = Todo.builder().content(content).build();
        todoRepository.save(todo);
    }



}
