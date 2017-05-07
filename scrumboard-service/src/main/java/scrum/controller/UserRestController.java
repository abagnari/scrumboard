package scrum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import scrum.exception.UserNotFoundException;
import scrum.pojo.Task;
import scrum.pojo.Account;
import scrum.repository.TaskRepository;
import scrum.repository.UserRepository;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/account/{user_id}")
public class UserRestController {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    UserRestController(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Optional<Account> readAccounts(@PathVariable Long user_id) {
        return this.userRepository.findByAccountId(user_id);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable Long user_id, @RequestBody Task input) {
        this.validateUser(user_id);
        
        return this.userRepository
                .findByAccountId(user_id)
                .map(account -> {
                    Task result = taskRepository.save(new Task(account,
                            input.getTitle(), input.getDescription()));

                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(result.getTaskId()).toUri();

                    return ResponseEntity.created(location).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    private void validateUser(Long user_id) {
        this.userRepository.findByAccountId(user_id).orElseThrow(
                () -> new UserNotFoundException(user_id));
    }
}
