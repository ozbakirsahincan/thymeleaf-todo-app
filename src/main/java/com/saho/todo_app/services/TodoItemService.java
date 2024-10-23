package com.saho.todo_app.services;

import java.util.Optional;

import com.saho.todo_app.models.TodoItem;

public interface TodoItemService {
    Iterable<TodoItem> getAll();
    Optional<TodoItem> getById(long id);
    TodoItem save(TodoItem todoItem);
    void delete(TodoItem todoItem);
}
