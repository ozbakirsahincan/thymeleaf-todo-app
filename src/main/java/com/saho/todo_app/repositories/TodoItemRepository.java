package com.saho.todo_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saho.todo_app.models.TodoItem;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}
