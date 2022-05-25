package Models;

import java.util.Objects;

public class SubTask extends Task {

    private int IdEpic;

    public SubTask(String NameTask, String DetailsTusk, taskStatus Status, int IdEpic) {
        super(NameTask, DetailsTusk, Status);
        this.IdEpic = IdEpic;
    }

    public SubTask(String NameTask, String DetailsTusk, taskStatus Status) {
        super(NameTask, DetailsTusk);
    }

    public int getIdEpic() {
        return IdEpic;
    }

    public void setIdEpic(int IDepic) {
        this.IdEpic = IDepic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return IdEpic == subTask.IdEpic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), IdEpic);
    }
}
