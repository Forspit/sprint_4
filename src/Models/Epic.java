package Models;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    private ArrayList<SubTask> subTaskList = new ArrayList<>(); // список подзадач эпика

    public Epic(String NameTask, String DetailsTusk, ArrayList<SubTask> subTaskList) {
        super(NameTask, DetailsTusk);
        this.subTaskList = subTaskList;
    }

    public Epic(String NameTask, String DetailsTusk) {
        super(NameTask, DetailsTusk);
    }

    public void setSubTaskList(SubTask subTask) {
        subTaskList.add(subTask);
    }

    public ArrayList<SubTask> getSubTaskList() {
        return subTaskList;
    }

    public void removeSubTaskList(SubTask subTask) {
        subTaskList.remove(subTask);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subTaskList, epic.subTaskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTaskList);
    }
}
