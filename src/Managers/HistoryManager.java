package Managers;

import Models.*;

import java.util.ArrayList;

public interface HistoryManager {//добавляем историю просмотров

    public void addTaskInHistory(Task task); // Добавление задачи в список просмотров

    public ArrayList<Task> getHistory();  //Получение списка просмотренных задач
}
