package estudo.com.api_tarefas.service;

import estudo.com.api_tarefas.database.model.User;
import estudo.com.api_tarefas.database.repository.UserRepository;
import estudo.com.api_tarefas.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private UserRepository userRepository;

    @Test
    void searchUser() {

        UUID uuid = UUID.randomUUID();
        User user = new User();
        user.setId(uuid);
        user.setUsername("username");
        user.setPassword("password");


        when(userRepository.findById(uuid))
                .thenReturn(Optional.of(user));

        UserResponse response = adminService.SearchUser(uuid);
        assertNotNull(response);
        assertEquals(user.getUsername(), response.username());


    }

    @Test
    void deleteUser() {

        UUID uuid = UUID.randomUUID();
        User user = new User();
        user.setId(uuid);
        user.setUsername("username");
        user.setPassword("password");


        when(userRepository.findById(uuid))
                .thenReturn(Optional.of(user));

        adminService.deleteUser(uuid);
        verify(userRepository).deleteById(uuid);

    }
}