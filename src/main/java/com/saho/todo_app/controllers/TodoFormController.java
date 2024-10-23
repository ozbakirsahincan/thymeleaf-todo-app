package com.saho.todo_app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.saho.todo_app.models.TodoItem;
import com.saho.todo_app.services.TodoItemServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TodoFormController {
    @Autowired
    private TodoItemServiceImpl todoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem) {

        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {

        TodoItem item = new TodoItem();
        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());

        todoItemService.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        TodoItem td = todoItemService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo item id: " + id + " not found"));
        todoItemService.delete(td);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TodoItem td = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo item id: " + id + " not found"));

        model.addAttribute("todo", td);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItem todoItem, BindingResult result,
            Model model) {
        TodoItem ti = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo item id: " + id + " not found"));

        ti.setIsComplete(todoItem.getIsComplete());
        ti.setDescription(todoItem.getDescription());

        todoItemService.save(ti);
        return "redirect:/";
    }

}
