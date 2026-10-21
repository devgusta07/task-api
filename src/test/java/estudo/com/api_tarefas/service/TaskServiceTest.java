package estudo.com.api_tarefas.service;

import estudo.com.api_tarefas.database.model.Task;
import estudo.com.api_tarefas.database.model.User;
import estudo.com.api_tarefas.database.repository.RoleRepository;
import estudo.com.api_tarefas.database.repository.TaskRepository;
import estudo.com.api_tarefas.database.repository.UserRepository;
import estudo.com.api_tarefas.dto.TaskRequest;
import estudo.com.api_tarefas.dto.TaskResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void createTask() {

        Date date = new Date(126, 9, 20, 20, 0);
        TaskRequest taskRequest = new TaskRequest("Estudar", date);

        UUID uuid = UUID.randomUUID();
        User user = new User();
        user.setId(uuid);

        when(userRepository.findById(uuid))
                .thenReturn(Optional.of(user));


        TaskResponse taskResponse = taskService.createTask(taskRequest, uuid);
        assertNotNull(taskResponse);
        assertEquals("Estudar", taskResponse.name());
        assertEquals(date, taskResponse.date());
        assertEquals(uuid, taskResponse.id_user());

        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void deleteTask() {

        UUID idUser = UUID.randomUUID();
        User user = new User();
        user.setId(idUser);

        UUID idTask = UUID.randomUUID();
        Task task = new Task();
        task.setId(idTask);
        task.setUser(user);

        when(taskRepository.findByIdAndUser_Id(idTask, idUser))
                .thenReturn(Optional.of(task));

        taskService.deleteTask(idUser, idTask);

        verify(taskRepository).delete(task);

    }

    @Test
    void updateTask() {

        UUID idUser = UUID.randomUUID();
        User user = new User();
        user.setId(idUser);

        UUID idTask = UUID.randomUUID();
        Task task = new Task();
        task.setId(idTask);
        task.setUser(user);

        Date date = new Date(126, 9, 20, 20, 0);
        TaskRequest taskRequest = new TaskRequest("Jogar", date);

        when(taskRepository.findByIdAndUser_Id(idTask, idUser))
                .thenReturn(Optional.of(task));


        TaskResponse taskResponse = taskService.updateTask(idTask, idUser, taskRequest);
        assertNotNull(taskResponse);
        assertEquals("Jogar", taskResponse.name());
        assertEquals(date, taskResponse.date());
        assertEquals(idUser, taskResponse.id_user());

        verify(taskRepository).save(any(Task.class));

    }

    @Test
    void getAllTasks() {

        UUID idUser = UUID.randomUUID();
        User user = new User();
        user.setId(idUser);

        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setName("Estudar Java");
        task.setDate(new Date(126, 9, 20, 20, 0));
        task.setUser(user);

        when(taskRepository.findByUserId(idUser))
                .thenReturn(List.of(task));

        List<TaskResponse> taskResponse = taskService.getAllTasks(idUser);
        assertNotNull(taskResponse);
        assertEquals("Estudar Java", taskResponse.get(0).name());
        assertEquals(new Date(126, 9, 20, 20, 0), taskResponse.get(0).date());
        assertEquals(idUser, taskResponse.get(0).id_user());

        verify(taskRepository).findByUserId(idUser);

    }
}