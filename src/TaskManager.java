import java.util.List;

public interface TaskManager {
    int generateId();
    Task createTask(Task task);
    Epic createEpic(Epic epic);
    Subtask createSubtask(Subtask subtask);
    List<Task> getAllTasks();
    List<Epic> getAllEpics();
    List<Subtask> getAllSubtasks();
    Task getTaskById(int id);
    Epic getEpicById(int id);
    Subtask getSubtaskById(int id);
    void updateTask(Task newTask);
    void updateEpic(Epic newEpic);
    void updateSubtask(Subtask newSubtask);
    void deleteTask(int id);
    void deleteEpic(int id);
    void deleteSubtask(int id);
    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubtasks();

    List<Subtask> getSubtasksByEpicId(int epicId);
    void updateEpicStatus(int epicId);
    List<Task> history();
}
