package scrum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scrum.pojo.Task;
import scrum.repository.TaskRepository;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/task/{taskId}")
public class TaskRestController {
    private final TaskRepository taskRepository;

    @Autowired
    TaskRestController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Optional<Task> readTasks(@PathVariable Long taskId) {
        return this.taskRepository.findByTaskId(taskId);
    }
}
