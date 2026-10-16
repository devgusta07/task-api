package estudo.com.api_tarefas.database.repository;

import estudo.com.api_tarefas.database.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {


    List<Task> findByUserId(UUID id);

    Optional<Task> findByIdAndUser_Id(UUID idTask, UUID idUser);
}
