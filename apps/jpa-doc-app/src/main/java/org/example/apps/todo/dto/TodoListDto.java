package org.example.apps.todo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rival
 * @since 2023-12-11
 */

@Data
public class TodoListDto
{
    private List<TodoInfoDto> todos = new ArrayList<>();
}
