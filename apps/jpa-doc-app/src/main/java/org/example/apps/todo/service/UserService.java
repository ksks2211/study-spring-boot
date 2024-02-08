package org.example.apps.todo.service;

import org.example.apps.todo.dto.UserForm;
import org.example.apps.todo.entity.User;
import org.example.apps.todo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author rival
 * @since 2023-12-11
 */
@Service
public class UserService {

    public void createUser(UserForm form){
        User entity = UserMapper.INSTANCE.toEntity(form);
        // userRepository.save(entity);
    }
}
