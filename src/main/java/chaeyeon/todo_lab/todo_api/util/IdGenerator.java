package chaeyeon.todo_lab.todo_api.util;

public class IdGenerator {
    private static Long idCounter = 0L;

    public static Long generateId(){
        return ++idCounter;
    }
}
