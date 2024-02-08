package org.example.apps.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.apps.todo.dto.TodoForm;
import org.example.apps.todo.dto.TodoInfoDto;
import org.example.apps.todo.entity.Todo;
import org.example.apps.todo.mapper.TodoMapper;
import org.example.apps.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rival
 * @since 2023-10-19
 */

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    @Override
    public void createTodo(TodoForm form) {
        Todo todo = TodoMapper.INSTANCE.toEntity(form);
        todoRepository.save(todo);
    }

    @Override
    public List<TodoInfoDto> findAll() {
        List<Todo> entities = todoRepository.findAll();
        return TodoMapper.INSTANCE.toDtoList(entities);
    }

}
