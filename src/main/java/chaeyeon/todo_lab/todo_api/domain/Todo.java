package chaeyeon.todo_lab.todo_api.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Todo {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDate dueDate;
    private String category;
    private Importance importance;
    private Boolean isDone;

    public Todo(Long id, String title, LocalDateTime createdAt, LocalDate dueDate, String category, Importance importance, Boolean isDone) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.category = category;
        this.importance = importance;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}