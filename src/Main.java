import java.util.List;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        TaskManager manager = Managers.getDefault();

        Task task1 = new Task("Помыть полы", "Завтра после пар", Status.NEW);
        Task task2 = new Task("Купить молоко", "", Status.NEW);

        Epic epic1 = new Epic("Построить дом", "До 30 лет");
        Epic epic2 = new Epic("Зарядить телефон", "Поставить телефон на зарядку");
        Epic epic3 = new Epic("Сдать лабораторную работу по серверным приложениям", "Защитить лабу");

        int idTask1 = manager.createTask(task1).getId();
        int idTask2 = manager.createTask(task2).getId();
        int idEpic1 = manager.createEpic(epic1).getId();
        int idEpic2 = manager.createEpic(epic2).getId();
        int idEpic3 = manager.createEpic(epic3).getId();

        Subtask subtask1 = new Subtask("Выровнять территорию", "Вызвать экскаватор", Status.NEW, idEpic1);
        Subtask subtask2 = new Subtask("Поставить дом", "Вызвать манипулятор", Status.NEW, idEpic1);
        Subtask subtask3 = new Subtask("Сделать отделку", "Закупить шпатлевку", Status.NEW, idEpic1);

        Subtask subtask4 = new Subtask("Подготовить код", "Код на языке Java", Status.NEW, idEpic3);
        Subtask subtask5 = new Subtask("Проверить код на уязвимости", "Пройти тестировку", Status.NEW, idEpic3);
        Subtask subtask6 = new Subtask("", "", Status.NEW, idEpic3);

        printHistory(manager, "Проверка");
        int idSub1 = manager.createSubtask(subtask1).getId();
        int idSub2 = manager.createSubtask(subtask2).getId();
        int idSub3 = manager.createSubtask(subtask3).getId();

        int idSub4 = manager.createSubtask(subtask4).getId();
        int idSub5 = manager.createSubtask(subtask5).getId();
        int idSub6 = manager.createSubtask(subtask6).getId();
        manager.getTaskById(idTask1);
        printHistory(manager, "После просмотра Задачи 1");

        manager.getEpicById(idEpic1);
        printHistory(manager, "После просмотра Эпика 1");

        manager.getSubtaskById(idSub1);
        printHistory(manager, "После просмотра Подзадачи 1");

        manager.getTaskById(idTask1);
        printHistory(manager, "После повторного просмотра Задачи 1");

        manager.getEpicById(idEpic2);
        printHistory(manager, "После просмотра Эпика 2");

        manager.getSubtaskById(idSub2);
        manager.getSubtaskById(idSub3);
        printHistory(manager, "После просмотра Подзадачи 2 и 3");

        manager.deleteTask(idTask1);
        printHistory(manager, "После удаления Задачи 1");

        manager.deleteEpic(idEpic1);
        printHistory(manager, "После удаления Эпика 1");

        manager.getSubtaskById(idSub4);
        manager.getSubtaskById(idSub5);
        manager.getSubtaskById(idSub6);
        manager.getEpicById(idEpic3);
        printHistory(manager, "После просмотра Эпика 3 и его подзадач");

        for (int i = 0; i < 100000; i++){
            Math.sqrt(i);
        }
        long endTime = System.nanoTime();
        long durationInNanos = endTime - startTime;
        double durationInMillis = durationInNanos / 1_000_000.0;

        System.out.println("Время выполнения: " + durationInMillis + " мс");
    }

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