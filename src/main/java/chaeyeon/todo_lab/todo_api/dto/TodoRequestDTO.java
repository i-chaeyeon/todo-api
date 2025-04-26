package chaeyeon.todo_lab.todo_api.dto;

import chaeyeon.todo_lab.todo_api.domain.Importance;
import chaeyeon.todo_lab.todo_api.domain.Todo;
import chaeyeon.todo_lab.todo_api.util.IdGenerator;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoRequestDTO {
    @NotBlank(message = "할일을 입력해주세요.")
    private final String title;

    @Future(message = "마감일은 현재 이후로 지정해주세요.")
    private final LocalDate dueDate;

    @NotBlank(message = "카테고리를 입력해주세요.")
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

    public String getTitle() {
        return title;
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
