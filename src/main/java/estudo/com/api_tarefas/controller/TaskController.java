package estudo.com.api_tarefas.controller;

import estudo.com.api_tarefas.dto.TaskRequest;
import estudo.com.api_tarefas.dto.TaskResponse;
import estudo.com.api_tarefas.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/creat")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest,
                                                   @AuthenticationPrincipal Jwt jwt) {

        UUID idUser = UUID.fromString(jwt.getSubject());

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskRequest, idUser));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID id,
                                                   @AuthenticationPrincipal Jwt jwt,
                                                   @RequestBody TaskRequest taskRequest) {

        UUID idUser = UUID.fromString(jwt.getSubject());

        return ResponseEntity.ok(taskService.updateTask(id, idUser, taskRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable UUID id,
            @AuthenticationPrincipal Jwt jwt) {

        UUID idUser = UUID.fromString(jwt.getSubject());

        taskService.deleteTask(id, idUser);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TaskResponse>> listTasks(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(taskService.getAllTasks(UUID.fromString(jwt.getSubject())));
    }
}
