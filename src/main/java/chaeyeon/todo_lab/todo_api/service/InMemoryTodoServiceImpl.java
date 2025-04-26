package chaeyeon.todo_lab.todo_api.service;

import chaeyeon.todo_lab.todo_api.domain.Importance;
import chaeyeon.todo_lab.todo_api.domain.Todo;
import chaeyeon.todo_lab.todo_api.dto.TodoRequestDTO;
import chaeyeon.todo_lab.todo_api.dto.TodoResponseDTO;
import chaeyeon.todo_lab.todo_api.repository.TodoRepository;

import java.time.LocalDate;
import java.util.List;

public class InMemoryTodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;  // 리포지토리 선언

    public InMemoryTodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;  // 생성자 주입
    }

    @Override
    public TodoResponseDTO createTodo(TodoRequestDTO todoRequestDTO) {
        Todo savedTodo = todoRequestDTO.toEntity();
        todoRepository.save(savedTodo);
        return TodoResponseDTO.fromEntity(savedTodo);
    }

    @Override
    public List<TodoResponseDTO> getAllTodos() {
        List<Todo> allTodos = todoRepository.findAll();
        return allTodos.stream()
                .map(TodoResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<TodoResponseDTO> getTodosByCategory(String category) {
        List<Todo> allTodos = todoRepository.findAll();
        return allTodos.stream()
                .filter(todo -> todo.getCategory().equals(category)) // 여기 필터링 추가
                .map(TodoResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<TodoResponseDTO> getTodosByImportance(Importance importance) {
        List<Todo> allTodos = todoRepository.findAll();
        return allTodos.stream()
                .filter(todo -> todo.getImportance().equals(importance)) // 여기 필터링 추가
                .map(TodoResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<TodoResponseDTO> getTodosDueBefore(LocalDate dueDate) {
        List<Todo> allTodos = todoRepository.findAll();
        return allTodos.stream()
                .filter(todo -> todo.getDueDate().isBefore(dueDate))
                .map(TodoResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<TodoResponseDTO> getTodosOverDue(LocalDate dueDate) {
        List<Todo> allTodos = todoRepository.findAll();
        return allTodos.stream()
                .filter(todo -> todo.getDueDate().isAfter(dueDate))
                .map(TodoResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<TodoResponseDTO> getTodosNotDone() {
        List<Todo> allTodos = todoRepository.findAll();
        return allTodos.stream()
                .filter(todo -> !todo.getDone())
                .map(TodoResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public TodoResponseDTO updateTodo(Long id, TodoRequestDTO todoRequestDTO) {
        Todo todo = todoRepository.findById(id);

        todo.setTitle(todoRequestDTO.getTitle());
        todo.setDueDate(todoRequestDTO.getDueDate());
        todo.setCategory(todoRequestDTO.getCategory());
        todo.setImportance(todoRequestDTO.getImportance());
        todo.setDone(todoRequestDTO.getDone());

        todoRepository.save(todo);
        return TodoResponseDTO.fromEntity(todo);
    }

    /// 아래처럼 구현하면 id가 새로 생길수도 있음 (toEntity 과정에서 IdGenerator가 id를 새로 생성할 가능성 존재)
//    @Override
//    public TodoResponseDTO updateTodo(Long id, TodoRequestDTO todoRequestDTO) {
//        Todo updatedTodo = todoRepository.findById(id);
//        updatedTodo = todoRequestDTO.toEntity();
//        todoRepository.save(updatedTodo);
//        return TodoResponseDTO.fromEntity(updatedTodo);
//    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
