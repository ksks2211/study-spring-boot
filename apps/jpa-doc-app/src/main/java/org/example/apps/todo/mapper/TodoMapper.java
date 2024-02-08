package org.example.apps.todo.mapper;

import org.example.apps.todo.dto.TodoForm;
import org.example.apps.todo.dto.TodoInfoDto;
import org.example.apps.todo.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author rival
 * @since 2023-12-11
 */
@Mapper
public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    Todo toEntity(TodoForm form);


    @Mapping(source = "content", target = "summary", qualifiedByName = "truncateContent")
    TodoInfoDto toDto(Todo entity);

    List<TodoInfoDto> toDtoList(List<Todo> entities);


    @Named("truncateContent")
    default String truncateContent(String content) {
        return content != null && content.length() > 100 ? content.substring(0, 100) : content;
    }

}
