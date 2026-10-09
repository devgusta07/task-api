package estudo.com.api_tarefas.database.repository;

import estudo.com.api_tarefas.database.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    Optional<Task> findByName(String name);
}
