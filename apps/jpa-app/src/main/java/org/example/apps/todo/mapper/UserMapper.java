package org.example.apps.todo.mapper;

import org.example.apps.todo.dto.UserForm;
import org.example.apps.todo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author rival
 * @since 2023-12-11
 */

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(source="password", target="password",  qualifiedByName = "hashPassword")
    User toEntity(UserForm form);

    @Named("hashPassword")
    default String hashPassword(String password) {
        // Simple hash function for demonstration
        // In production, use a strong hashing algorithm like BCrypt
        return password != null ? Integer.toHexString(password.hashCode()) : null;
    }
}

