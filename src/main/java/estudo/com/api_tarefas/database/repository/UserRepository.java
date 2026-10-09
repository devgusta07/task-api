package estudo.com.api_tarefas.database.repository;
import estudo.com.api_tarefas.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
