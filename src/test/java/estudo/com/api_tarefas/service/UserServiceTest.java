package estudo.com.api_tarefas.service;

import estudo.com.api_tarefas.database.model.Role;
import estudo.com.api_tarefas.database.model.RoleName;
import estudo.com.api_tarefas.database.model.User;
import estudo.com.api_tarefas.database.repository.RoleRepository;
import estudo.com.api_tarefas.database.repository.UserRepository;
import estudo.com.api_tarefas.dto.UserRequest;
import estudo.com.api_tarefas.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void createUser() {

        UserRequest userRequest = new UserRequest("username", "password");

        Role role = new Role();
        role.setRoleName(RoleName.BASIC);

        when(userRepository.existsByUsername("username"))
                .thenReturn(false);

        when(passwordEncoder.encode("password"))
                .thenReturn("password");

        when(roleRepository.findByRoleName(RoleName.BASIC))
                .thenReturn(Optional.of(role));

        UserResponse userResponse = userService.createUser(userRequest);
        assertNotNull(userResponse);
        assertEquals("username", userResponse.username());

        verify(userRepository).save(any(User.class));

    }



}