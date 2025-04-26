package chaeyeon.todo_lab.todo_api.service;

import chaeyeon.todo_lab.todo_api.domain.Importance;
import chaeyeon.todo_lab.todo_api.domain.Todo;
import chaeyeon.todo_lab.todo_api.dto.TodoRequestDTO;
import chaeyeon.todo_lab.todo_api.dto.TodoResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {

    TodoResponseDTO createTodo(TodoRequestDTO todoRequestDTO);

    List<TodoResponseDTO> getAllTodos();
    List<TodoResponseDTO> getTodosByCategory(String category);
    List<TodoResponseDTO> getTodosByImportance(Importance importance);
    List<TodoResponseDTO> getTodosDueBefore(LocalDate dueDate);
    List<TodoResponseDTO> getTodosOverDue(LocalDate dueDate);
    List<TodoResponseDTO> getTodosNotDone();

    TodoResponseDTO updateTodo(Long id, TodoRequestDTO todoRequestDTO);

    void deleteTodo(Long id);
}
