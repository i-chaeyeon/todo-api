package chaeyeon.todo_lab.todo_api.dto;

import chaeyeon.todo_lab.todo_api.domain.Importance;
import chaeyeon.todo_lab.todo_api.domain.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoResponseDTO {
    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDate dueDate;
    private final String category;
    private final Importance importance;
    private final Boolean isDone;

    public TodoResponseDTO(Long id, String title, LocalDateTime createdAt, LocalDate dueDate, String category, Importance importance, Boolean isDone) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.category = category;
        this.importance = importance;
        this.isDone = isDone;
    }

    public static TodoResponseDTO fromEntity(Todo todo){
        return new TodoResponseDTO(todo.getId(), todo.getTitle(), todo.getCreatedAt(), todo.getDueDate(), todo.getCategory(), todo.getImportance(), todo.getDone());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getCategory() {
        return category;
    }

    public Importance getImportance() {
        return importance;
    }

    public Boolean getDone() {
        return isDone;
    }
}
