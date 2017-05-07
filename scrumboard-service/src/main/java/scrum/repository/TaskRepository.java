package scrum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scrum.pojo.Task;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskId(Long taskId);
}