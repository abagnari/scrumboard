package scrum.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="accountId", unique=true, nullable=false)
    private long accountId;
    private String name;
    @OneToMany(mappedBy = "account")
    private Set<Task> tasks = new HashSet<>();

    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
