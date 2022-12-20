package io.comwork.todo.api.services;

import io.comwork.todo.api.model.Todo;
import io.comwork.todo.api.model.Todos;

import java.util.List;

public interface TodoService {
    Todos getTodos();

    Todo getTodoById(Long id);

    Todo insert(Todo todo);

    void updateTodo(Long id, Todo todo);

    void deleteTodo(Long todoId);
}
