package org.example.apps.todo.repository;

import org.example.apps.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rival
 * @since 2023-10-19
 */
public interface TodoRepository extends JpaRepository<Todo,Long>  {
}
