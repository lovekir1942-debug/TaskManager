import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Получаем менеджер задач через фабрику
        TaskManager manager = Managers.getDefault();

        // 2. Создаем задачи разного типа
        Task task1 = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        Task task2 = new Task("Задача 2", "Описание задачи 2", Status.NEW);

        Epic epic1 = new Epic("Эпик 1 (с подзадачами)", "Описание эпика 1");
        Epic epic2 = new Epic("Эпик 2 (без подзадач)", "Описание эпика 2");

        int idTask1 = manager.createTask(task1).getId();
        int idTask2 = manager.createTask(task2).getId();
        int idEpic1 = manager.createEpic(epic1).getId();
        int idEpic2 = manager.createEpic(epic2).getId();

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", Status.NEW, idEpic1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", Status.NEW, idEpic1);
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 3", Status.NEW, idEpic1);

        int idSub1 = manager.createSubtask(subtask1).getId();
        int idSub2 = manager.createSubtask(subtask2).getId();
        int idSub3 = manager.createSubtask(subtask3).getId();

        // 3. Имитируем просмотры и после каждого печатаем историю
        System.out.println("--- Начинаем просмотры ---");

        manager.getTaskById(idTask1);
        printHistory(manager, "После просмотра Задачи 1");

        manager.getEpicById(idEpic1);
        printHistory(manager, "После просмотра Эпика 1");

        manager.getSubtaskById(idSub1);
        printHistory(manager, "После просмотра Подзадачи 1");

        // Повторный просмотр Задачи 1 — она должна переместиться в конец, без дубля
        manager.getTaskById(idTask1);
        printHistory(manager, "После ПОВТОРНОГО просмотра Задачи 1");

        manager.getEpicById(idEpic2);
        printHistory(manager, "После просмотра Эпика 2");

        manager.getSubtaskById(idSub2);
        printHistory(manager, "После просмотра Подзадачи 2");

        // 4. Проверяем удаление из истории
        System.out.println("\n--- Проверяем удаление задач ---");

        // Удаляем задачу, которая есть в истории
        manager.deleteTask(idTask1);
        printHistory(manager, "После удаления Задачи 1");

        // Удаляем эпик с подзадачами — из истории должны исчезнуть и эпик, и все его подзадачи
        manager.deleteEpic(idEpic1);
        printHistory(manager, "После удаления Эпика 1 (с подзадачами)");
    }

    // Вспомогательный метод для красивого вывода истории
    private static void printHistory(TaskManager manager, String comment) {
        System.out.println("\n" + comment + ":");
        List<Task> history = manager.history();

        if (history.isEmpty()) {
            System.out.println("  (история пуста)");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + history.get(i));
            }
        }
    }
}