package estudo.com.api_tarefas.service;

import estudo.com.api_tarefas.database.model.User;
import estudo.com.api_tarefas.database.repository.UserRepository;
import estudo.com.api_tarefas.dto.UserResponse;
import estudo.com.api_tarefas.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    private final UserRepository userRepository;
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse SearchUser(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found!"
                ));

        return new UserResponse(
                user.getId(),
                user.getUsername()
        );

    }

    public void deleteUser(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found!"
                ));

        userRepository.delete(user);

    }
}
