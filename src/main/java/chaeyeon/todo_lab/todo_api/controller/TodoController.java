package chaeyeon.todo_lab.todo_api.controller;

import chaeyeon.todo_lab.todo_api.domain.Importance;
import chaeyeon.todo_lab.todo_api.domain.Todo;
import chaeyeon.todo_lab.todo_api.dto.TodoRequestDTO;
import chaeyeon.todo_lab.todo_api.dto.TodoResponseDTO;
import chaeyeon.todo_lab.todo_api.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDTO> createTodo(@RequestBody @Valid TodoRequestDTO todoRequestDTO){
        TodoResponseDTO response = todoService.createTodo(todoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getTodos(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Importance importance,
            @RequestParam(required = false) Boolean isDone
            ){
        List<TodoResponseDTO> response = todoService.getAllTodos();

        if(category != null){
            response = response.stream()
                    .filter(r -> r.getCategory().equals(category))
                    .toList();
        }

        if(importance != null){
            response = response.stream()
                    .filter(r -> r.getImportance().equals(importance))
                    .toList();
        }

        if(isDone != null){
            response = response.stream()
                    .filter(r -> r.getDone().equals(isDone))
                    .toList();
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> updateTodo(
            @PathVariable Long id,
            @RequestBody @Valid TodoRequestDTO todoRequestDTO
    ){
        TodoResponseDTO response = todoService.updateTodo(id, todoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build(); 
    }

}
