package Managers;

import Models.*;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private HistoryManager historyManager = Managers.getDefaultHistory();
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epicTasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private int id = 1;

    @Override
    public void getHistory() {//если честно не совсем понял тут( зачем в двух интерфейcах одинаковый метод и вызывать его так
        historyManager.getHistory();
    }

    @Override
    public Task getTaskById(Integer Id) {  // Получение задачи по ID, опять запихал в один метод getEpic и getsub((
        if (tasks.isEmpty() && subTasks.isEmpty() && epicTasks.isEmpty()) { //проверка на наличие задач
            return null;
        }
        if (!tasks.containsKey(Id) && subTasks.containsKey(Id) && epicTasks.containsKey(Id)) { //нет задач с данным id
            return null;
        }
        Task taskById = tasks.get(Id);
        if (!(taskById instanceof Epic) && !(taskById instanceof SubTask)) {
            historyManager.addTaskInHistory(taskById);
            return taskById;
        }
        if (taskById instanceof SubTask) {
            SubTask subtask = (SubTask) taskById;
            historyManager.addTaskInHistory(subtask);
            return subtask;
        }
        Epic epic = (Epic) taskById;
        historyManager.addTaskInHistory(epic);
        return epic;
    }

    @Override
    public void addTask(Task task) { //сохранить обычную задачу
        tasks.put(id, task);
        task.setId(id);
        id++;
    }

    @Override
    public void addEpic(Epic epic) { //сохранить эпическую задачу
        epicTasks.put(id, epic);
        epic.setId(id);
        id++;
        refreshStatus(epic);
    }

    @Override
    public void addSubTask(SubTask subTask) {//сохранить подзадачу
        if (subTask.getIdEpic() != 0) {
            subTasks.put(id, subTask);
            subTask.setId(id);
            id++;
            int IdEpic = subTask.getIdEpic();
            epicTasks.get(IdEpic).setSubTaskList(subTask);
            refreshStatus(epicTasks.get(IdEpic));
        } else {
            System.out.println("Эпика с таким id нет");
        }
    }

    @Override
    public ArrayList<Task> getAllTaskList() {
        ArrayList<Task> AllTaskList = new ArrayList<>();
        for (Integer id : tasks.keySet()) {
            AllTaskList.add(tasks.get(id));
        }
        for (Integer id : epicTasks.keySet()) {
            AllTaskList.add(epicTasks.get(id));
        }
        for (Integer id : subTasks.keySet()) {
            AllTaskList.add(subTasks.get(id));
        }
        return AllTaskList;
    }

    @Override
    public void deleteAllTask() { //удаление всех здач
        tasks.clear();
        epicTasks.clear();
        subTasks.clear();
    }

    @Override
    public void updateTaskById(Task task) { //обновление задачи по нидификатору
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateEpicTaskById(Epic epic) { //обновление эпика по нидификатору
        epicTasks.put(epic.getId(), epic);
    }

    @Override
    public void updateSubTaskById(SubTask subTask) //сохранить подзадачу по id
    {
        if (subTask.getIdEpic() != 0) {
            subTasks.put(subTask.getId(), subTask);
            int IdEpic = subTask.getIdEpic();
            epicTasks.get(IdEpic).setSubTaskList(subTask);
            refreshStatus(epicTasks.get(IdEpic));
        } else {
            System.out.println("Эпика с таким id нет");
        }
    }

    @Override
    public void deleteTaskId(int ID) { //удаление задачи по id
        if (tasks.containsKey(ID)) {
            tasks.remove(ID);
            if (epicTasks.containsKey(ID)) {
                epicTasks.remove(ID);
            }
            if (subTasks.containsKey(ID)) {
                int IdEpic = subTasks.get(ID).getIdEpic();
                epicTasks.get(IdEpic).removeSubTaskList(subTasks.get(ID));//удаление подзадачи из листа эпиков
                subTasks.remove(ID);
                refreshStatus(epicTasks.get(IdEpic));
            }
        }
    }

    @Override
    public ArrayList<SubTask> getAllSubTaskById(int ID) {//получение списка подзадач эпика по индификатору
        return epicTasks.get(ID).getSubTaskList();
    }

    private void refreshStatus(Epic epic) { //обновление статуса эпика
        int checkStatusNew = 0;
        int checkStatusDone = 0;
        ArrayList<SubTask> subTaskList = epic.getSubTaskList();
        for (SubTask listSubTask : subTaskList) {
            if (listSubTask.getStatus().equals(taskStatus.NEW)) {
                checkStatusNew++;
                if (checkStatusNew == subTaskList.size()) {
                    epic.setStatus(taskStatus.NEW);
                } else epic.setStatus(taskStatus.IN_PROGRESS);
            } else if (listSubTask.getStatus().equals(taskStatus.DONE)) {
                checkStatusDone++;
                if (checkStatusDone == subTaskList.size()) {
                    epic.setStatus(taskStatus.DONE);
                } else epic.setStatus(taskStatus.IN_PROGRESS);
            }
        }
    }
}
