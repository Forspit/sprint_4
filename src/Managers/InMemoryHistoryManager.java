package Managers;

import Models.*;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private ArrayList<Task> historyTaskList = new ArrayList<>();

    @Override
    public void addTaskInHistory(Task task) { // Добавление задачи в список просмотров
        historyTaskList.add(task);
        if (historyTaskList.size() > 10) {
            historyTaskList.remove(0);
        }
    }

    @Override
    public ArrayList<Task> getHistory() {  //Получение списка просмотренных задач
        return historyTaskList;
    }
}
