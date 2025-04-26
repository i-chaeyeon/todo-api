package chaeyeon.todo_lab.todo_api.repository;

import chaeyeon.todo_lab.todo_api.domain.Todo;

import java.util.List;

public interface TodoRepository {
    void save(Todo todo);
    Todo findById(Long id);
    List<Todo> findAll();
    void deleteById(Long id);
}
