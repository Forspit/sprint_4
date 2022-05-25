package Managers;
import Models.*;
import java.util.ArrayList;

public interface TaskManager {

    public void addTask(Task task);//сохранить обычную задачу

    public void addEpic(Epic epic);//сохранить эпическую задачу

    public void addSubTask(SubTask subTask);//сохранить подзадачу

    public ArrayList<Task> getAllTaskList();//получениие списка всех задач

    public void deleteAllTask();//удаление всех здач

    public Task getTaskById(Integer ID);//получение по индификатору

    public void updateTaskById(Task task);//сохранить задачу по id

    public void updateEpicTaskById(Epic epic);//сохранить эпик по id

    public void updateSubTaskById(SubTask subTask);//сохранить подзадачу по id

    public void deleteTaskId(int ID);//удаление задачи по id

    public ArrayList<SubTask> getAllSubTaskById(int ID);//получить подзадачу по id

    public void getHistory();
}
