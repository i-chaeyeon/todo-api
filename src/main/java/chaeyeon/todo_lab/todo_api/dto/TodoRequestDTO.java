package chaeyeon.todo_lab.todo_api.dto;

import chaeyeon.todo_lab.todo_api.domain.Importance;
import chaeyeon.todo_lab.todo_api.domain.Todo;
import chaeyeon.todo_lab.todo_api.util.IdGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoRequestDTO {
    private final String title;
    private final LocalDate dueDate;
    private final String category;
    private final Importance importance;
    private final Boolean isDone;

    public TodoRequestDTO(String title, LocalDate dueDate, String category, Importance importance, Boolean isDone) {
        this.title = title;
        this.dueDate = dueDate;
        this.category = category;
        this.importance = importance;
        this.isDone = isDone;
    }

    public Todo toEntity(){
        return new Todo(IdGenerator.generateId(), title, LocalDateTime.now(), dueDate, category, importance, isDone);
    }
}
