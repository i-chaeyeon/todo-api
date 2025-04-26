package chaeyeon.todo_lab.todo_api.repository;

import chaeyeon.todo_lab.todo_api.domain.Importance;
import chaeyeon.todo_lab.todo_api.domain.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InMemoryTodoRepositoryTest {

    private InMemoryTodoRepository repository;

    @BeforeEach
    void setRepository(){
        repository = new InMemoryTodoRepository();
    }

    @Test
    void 할일_추가하기() {
        // given
        Todo todo = new Todo(
                1L,
                "테스트 1",
                LocalDateTime.now(),
                LocalDate.now().plusDays(1),
                "테스트",
                Importance.MEDIUM,
                false);

        // when
        repository.save(todo);
        Todo findTodo = repository.findById(1L);

        //then
        assertThat(todo).isEqualTo(findTodo); // 지금은 동일 객체여서 통과함 근데 이렇게 짜는게 바람직하지는 않음 (== vs equals)
        assertThat(findTodo.getTitle()).isEqualTo("테스트 1"); // 필드 직접 비교하는 방식이 더 안전
    }

    @Test
    void 할일_삭제하기(){
        // given
        Todo todo = new Todo(
                1L,
                "테스트 1",
                LocalDateTime.now(),
                LocalDate.now().plusDays(1),
                "테스트",
                Importance.MEDIUM,
                false);

        // when
        repository.save(todo);
        repository.deleteById(1L);
        Todo findTodo = repository.findById(1L);

        //then
        assertThat(findTodo).isNull();
    }

    @Test
    void 할일_전체_반환하기(){
        // given
        Todo todo1 = new Todo(
                1L,
                "테스트 1",
                LocalDateTime.now(),
                LocalDate.now().plusDays(1),
                "테스트",
                Importance.MEDIUM,
                false);
        Todo todo2 = new Todo(
                2L,
                "테스트 2",
                LocalDateTime.now(),
                LocalDate.now().plusDays(1),
                "테스트",
                Importance.HIGH,
                false);


        // when
        repository.save(todo1);
        repository.save(todo2);
        List<Todo> findTodo = repository.findAll();

        //then
        assertThat(findTodo).isNotNull();
        assertThat(findTodo).hasSize(2);
        assertThat(findTodo).extracting("id")
                .containsExactlyInAnyOrder(1L, 2L);
        assertThat(findTodo).extracting("title")
                .containsExactlyInAnyOrder("테스트 1", "테스트 2");
    }

    @Test
    void 할일_없을떄_빈_리스트_반환하기() {
        // given

        // when
        List<Todo> findTodo = repository.findAll();

        // then
        assertThat(findTodo).isEmpty();
    }
}