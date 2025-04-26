package chaeyeon.todo_lab.todo_api.dto;

import chaeyeon.todo_lab.todo_api.domain.Importance;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

class TodoRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void 할일은_비어있을_수_없다(){
        TodoRequestDTO dto = new TodoRequestDTO(
                "",
                LocalDate.now().plusDays(1),
                "공부",
                Importance.HIGH,
                false);

        Set<ConstraintViolation<TodoRequestDTO>> violations = validator.validate(dto);

        Assertions.assertThat(violations).isNotEmpty()
                .anyMatch(v -> v.getPropertyPath().toString().equals("title")
                            && v.getMessage().contains("할일을 입력해주세요."));
    }

    @Test
    public void 마감일은_현재보다_이를_수_없다(){
        TodoRequestDTO dto = new TodoRequestDTO(
                "출근하기",
                LocalDate.now().minusDays(1),
                "출근",
                Importance.HIGH,
                false);

        Set<ConstraintViolation<TodoRequestDTO>> violations = validator.validate(dto);

        Assertions.assertThat(violations).isNotEmpty()
                .anyMatch(v -> v.getPropertyPath().toString().equals("dueDate")
                        && v.getMessage().contains("마감일은 현재 이후로 지정해주세요."));
    }

    @Test
    public void 카테고리는_비어있을_수_없다(){
        TodoRequestDTO dto = new TodoRequestDTO(
                "출근하기",
                LocalDate.now().plusDays(1),
                "",
                Importance.HIGH,
                false);

        Set<ConstraintViolation<TodoRequestDTO>> violations = validator.validate(dto);

        Assertions.assertThat(violations).isNotEmpty()
                .anyMatch(v -> v.getPropertyPath().toString().equals("category")
                        && v.getMessage().contains("카테고리를 입력해주세요."));
    }

    @Test
    public void 중요도는_유효성_검사를_하지_않는다(){
        TodoRequestDTO dto = new TodoRequestDTO(
                "출근하기",
                LocalDate.now().plusDays(1),
                "출근",
                null,
                false);

        Set<ConstraintViolation<TodoRequestDTO>> violations = validator.validate(dto);

        Assertions.assertThat(violations).isEmpty();
    }
}