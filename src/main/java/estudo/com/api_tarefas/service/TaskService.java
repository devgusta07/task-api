package estudo.com.api_tarefas.service;

import estudo.com.api_tarefas.database.model.Task;
import estudo.com.api_tarefas.database.model.User;
import estudo.com.api_tarefas.database.repository.TaskRepository;
import estudo.com.api_tarefas.database.repository.UserRepository;
import estudo.com.api_tarefas.dto.TaskRequest;
import estudo.com.api_tarefas.dto.TaskResponse;
import estudo.com.api_tarefas.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskResponse createTask(TaskRequest taskRequest, UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found"
                ));

        Task newTask = new Task();
        newTask.setName(taskRequest.name());
        newTask.setDate(taskRequest.date());
        newTask.setUser(user);

        taskRepository.save(newTask);

        return new TaskResponse(
                newTask.getId(),
                newTask.getName(),
                newTask.getDate(),
                newTask.getUser().getId()
        );

    }

    public void deleteTask(UUID idUser,UUID taskId) {

        Task task = taskRepository.findByIdAndUser_Id(taskId, idUser)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found"
                ));

        taskRepository.delete(task);
    }

    public TaskResponse updateTask(UUID taskId, UUID idUser, TaskRequest taskRequest) {

        Task task = taskRepository.findByIdAndUser_Id(taskId, idUser)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found"
                ));

        task.setName(taskRequest.name());
        task.setDate(taskRequest.date());

        taskRepository.save(task);

        return new TaskResponse(
                task.getId(),
                task.getName(),
                task.getDate(),
                task.getUser().getId()
        );

    }

    public List<TaskResponse> getAllTasks(UUID Userid) {

      List<Task> tasks = taskRepository.findByUserId(Userid);

      return tasks.stream()
              .map(task -> new TaskResponse(
                      task.getId(),
                      task.getName(),
                      task.getDate(),
                      task.getUser().getId()

              ))
              .toList();
    }
}
