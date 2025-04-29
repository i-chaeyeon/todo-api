package chaeyeon.todo_lab.todo_api.service;

import chaeyeon.todo_lab.todo_api.domain.Importance;
import chaeyeon.todo_lab.todo_api.dto.TodoRequestDTO;
import chaeyeon.todo_lab.todo_api.dto.TodoResponseDTO;
import chaeyeon.todo_lab.todo_api.repository.InMemoryTodoRepository;
import chaeyeon.todo_lab.todo_api.repository.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTodoServiceImplTest {

    InMemoryTodoServiceImpl inMemoryTodoService;

    @BeforeEach
    void setService(){
        TodoRepository todoRepository = new InMemoryTodoRepository();
        inMemoryTodoService = new InMemoryTodoServiceImpl(todoRepository);
    }

    @Test
    void 할일_생성하고_조회하기(){
        // given
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO(
                "스프링 공부하기",
                LocalDate.now().plusDays(1),
                "스프링",
                Importance.HIGH,
                false
        );

        // when
        TodoResponseDTO createdTodo = inMemoryTodoService.createTodo(todoRequestDTO);
        List<TodoResponseDTO> foundLowTodos = inMemoryTodoService.getAllTodos();

        // then
        assertThat(foundLowTodos).hasSize(1);
        assertThat(foundLowTodos.get(0).getId()).isEqualTo(createdTodo.getId());
        assertThat(foundLowTodos.get(0).getTitle()).isEqualTo("스프링 공부하기");
    }

    @Test
    void 할일_수정하기(){
        // given
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO(
                "스프링 공부하기",
                LocalDate.now().plusDays(1),
                "스프링",
                Importance.HIGH,
                false
        );

        // when
        TodoResponseDTO createdTodo = inMemoryTodoService.createTodo(todoRequestDTO);

        TodoRequestDTO updatedRequestDTO = new TodoRequestDTO(
                "스프링 공부하기!!(수정됨)",
                LocalDate.now().plusDays(2),
                "스프링",
                Importance.HIGH,
                false
        );
        TodoResponseDTO updatedTodo = inMemoryTodoService.updateTodo(createdTodo.getId(), updatedRequestDTO);

        // then
        assertThat(updatedTodo.getTitle()).isNotEqualTo(createdTodo.getTitle());
        assertThat(updatedTodo.getId()).isEqualTo(createdTodo.getId());
    }

    @Test
    void 없는_할일을_수정하면_예외(){
        // given
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO(
                "스프링 공부하기",
                LocalDate.now().plusDays(1),
                "스프링",
                Importance.HIGH,
                false
        );

        // when
        TodoResponseDTO createdTodo = inMemoryTodoService.createTodo(todoRequestDTO);

        TodoRequestDTO updatedRequestDTO = new TodoRequestDTO(
                "스프링 공부하기!!(수정됨)",
                LocalDate.now().plusDays(2),
                "스프링",
                Importance.HIGH,
                false
        );

        // then
        assertThatThrownBy(() -> {
            inMemoryTodoService.updateTodo(999L, updatedRequestDTO);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void 할일_삭제하기(){
        // given
        TodoRequestDTO todoRequestDTO1 = new TodoRequestDTO(
                "스프링 공부하기",
                LocalDate.now().plusDays(1),
                "스프링",
                Importance.HIGH,
                false
        );
        TodoRequestDTO todoRequestDTO2 = new TodoRequestDTO(
                "스프링 공부하기!!(수정됨)",
                LocalDate.now().plusDays(2),
                "스프링",
                Importance.HIGH,
                false
        );
        TodoRequestDTO todoRequestDTO3 = new TodoRequestDTO(
                "세번째 스프링 공부하기~",
                LocalDate.now().plusDays(2),
                "스프링",
                Importance.LOW,
                false
        );

        // when
        TodoResponseDTO createdTodo1 = inMemoryTodoService.createTodo(todoRequestDTO1);
        TodoResponseDTO createdTodo2 = inMemoryTodoService.createTodo(todoRequestDTO2);
        TodoResponseDTO createdTodo3 = inMemoryTodoService.createTodo(todoRequestDTO3);
        inMemoryTodoService.deleteTodo(createdTodo2.getId());
        List<TodoResponseDTO> foundLowTodos = inMemoryTodoService.getAllTodos();

        // then
        assertThat(foundLowTodos).hasSize(2);
        assertThat(foundLowTodos.get(0).getId()).isEqualTo(createdTodo1.getId());
        assertThat(foundLowTodos.get(1).getId()).isEqualTo(createdTodo3.getId());
        assertThat(foundLowTodos.get(1).getTitle()).isEqualTo("세번째 스프링 공부하기~");
    }

    @Test
    void 중요도_별로_할일_조회하기(){
        // given
        TodoRequestDTO todoRequestDTO1 = new TodoRequestDTO(
                "스프링 공부하기",
                LocalDate.now().plusDays(1),
                "스프링",
                Importance.HIGH,
                false
        );
        TodoRequestDTO todoRequestDTO2 = new TodoRequestDTO(
                "스프링 공부하기!!(수정됨)",
                LocalDate.now().plusDays(2),
                "스프링",
                Importance.HIGH,
                false
        );
        TodoRequestDTO todoRequestDTO3 = new TodoRequestDTO(
                "세번째 스프링 공부하기~",
                LocalDate.now().plusDays(2),
                "스프링",
                Importance.LOW,
                false
        );

        // when
        TodoResponseDTO createdTodo1 = inMemoryTodoService.createTodo(todoRequestDTO1);
        TodoResponseDTO createdTodo2 = inMemoryTodoService.createTodo(todoRequestDTO2);
        TodoResponseDTO createdTodo3 = inMemoryTodoService.createTodo(todoRequestDTO3);
        List<TodoResponseDTO> foundLowTodos = inMemoryTodoService.getTodosByImportance(Importance.LOW);
        List<TodoResponseDTO> foundHighTodos = inMemoryTodoService.getTodosByImportance(Importance.HIGH);

        // then
        assertThat(foundLowTodos).hasSize(1);
        assertThat(foundLowTodos.get(0).getId()).isNotEqualTo(createdTodo1.getId());
        assertThat(foundLowTodos.get(0).getId()).isEqualTo(createdTodo3.getId());
        assertThat(foundLowTodos.get(0).getTitle()).isEqualTo("세번째 스프링 공부하기~");

        assertThat(foundHighTodos).hasSize(2);
        assertThat(foundHighTodos.get(0).getId()).isEqualTo(createdTodo1.getId());
        assertThat(foundHighTodos.get(1).getId()).isEqualTo(createdTodo2.getId());
        assertThat(foundHighTodos.get(1).getTitle()).isEqualTo("스프링 공부하기!!(수정됨)");
    }
}