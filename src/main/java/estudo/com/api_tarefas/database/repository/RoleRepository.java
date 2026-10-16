package estudo.com.api_tarefas.database.repository;

import estudo.com.api_tarefas.database.model.Role;
import estudo.com.api_tarefas.database.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByRoleName(RoleName name);
}
