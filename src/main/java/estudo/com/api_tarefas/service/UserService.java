package estudo.com.api_tarefas.service;

import estudo.com.api_tarefas.database.model.Role;
import estudo.com.api_tarefas.database.model.RoleName;
import estudo.com.api_tarefas.database.model.User;
import estudo.com.api_tarefas.database.repository.RoleRepository;
import estudo.com.api_tarefas.database.repository.UserRepository;
import estudo.com.api_tarefas.dto.UserRequest;
import estudo.com.api_tarefas.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserResponse createUser(UserRequest request) {

        if(userRepository.existsByUsername(request.username())){

           throw new RuntimeException("Existing username");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));

        Role basic = roleRepository.findByRoleName(RoleName.BASIC)
                .orElseThrow(() -> new RuntimeException(
                        "Role not found"
                ));
        user.getRoles().add(basic);
        userRepository.save(user);

        return new UserResponse(
                user.getId(),
                user.getUsername()
        );
    }
}
