package scrum.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "task")
public class Task implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="taskId", unique=true, nullable=false)
    private long taskId;
    private String title, description;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="accountId")
    private Account account;

    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Task(Account account, String title, String description) {
        this.account = account;
        this.title = title;
        this.description = description;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
