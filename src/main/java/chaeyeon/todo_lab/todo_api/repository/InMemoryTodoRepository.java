package chaeyeon.todo_lab.todo_api.repository;

import chaeyeon.todo_lab.todo_api.domain.Todo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTodoRepository implements TodoRepository {

    private final Map<Long, Todo> store = new HashMap<>(); // private final 선언해주는것이 좋음

    @Override
    public void save(Todo todo) {
        store.put(todo.getId(), todo);
    }

    @Override
    public Todo findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Todo> findAll() {
        Collection<Todo> values = store.values();
        return values.stream().toList();
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
