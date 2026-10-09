package estudo.com.api_tarefas.controller;

import estudo.com.api_tarefas.dto.TaskRequest;
import estudo.com.api_tarefas.dto.TaskResponse;
import estudo.com.api_tarefas.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID id, @RequestBody TaskRequest taskRequest) {

        return ResponseEntity.ok(taskService.updateTask(id,taskRequest));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TaskResponse>> listTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
