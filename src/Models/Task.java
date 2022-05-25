package Models;

import java.util.Objects;

public class Task {

    private int Id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Id == task.Id && Objects.equals(name, task.name) && Objects.equals(details, task.details) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, details, status);
    }

    private String name; //название задачи
    private String details; //описание задачи
    private taskStatus status; // статус задачи, NEW, IN_PROGRESS, DONE

    public Task(String NameTask, String DetailsTusk, taskStatus Status) {
        this.name = NameTask;
        this.details = DetailsTusk;
        this.status = Status;
    }

    public Task(String NameTask, String DetailsTusk) {
        this.name = NameTask;
        this.details = DetailsTusk;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setStatus(taskStatus status) {
        this.status = status;
    }

    public taskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ID задачи Models.Task=\"" + Id + "\", Название задачи=\"" + name + "\", Описание=\"" + details
                + "\", Статус=\"" + status + "\n";
    }
}

