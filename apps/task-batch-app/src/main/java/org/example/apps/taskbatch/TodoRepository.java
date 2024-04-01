package org.example.apps.taskbatch;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rival
 * @since 2024-02-17
 */
public interface TodoRepository extends JpaRepository<Todo,Long> {
}
