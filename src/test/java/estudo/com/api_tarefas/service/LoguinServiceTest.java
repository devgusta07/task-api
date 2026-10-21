package estudo.com.api_tarefas.service;

import estudo.com.api_tarefas.database.model.Role;
import estudo.com.api_tarefas.database.model.RoleName;
import estudo.com.api_tarefas.database.model.User;
import estudo.com.api_tarefas.database.repository.UserRepository;
import estudo.com.api_tarefas.dto.LoguinRequest;
import estudo.com.api_tarefas.dto.LoguinResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoguinServiceTest {

    @InjectMocks
    private LoguinService loguinService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtEncoder jwtEncoder;

    @Test
    void loguin() {

        UUID idUser = UUID.randomUUID();

        LoguinRequest loguinRequest = new LoguinRequest
                ("username", "password");

        Role role = new Role();
        role.setRoleName(RoleName.BASIC);

        User user = new User();
        user.setId(idUser);
        user.setUsername("username");
        user.setPassword("password");
        user.getRoles().add(role);


        when(userRepository.findByUsername(loguinRequest.username()))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(loguinRequest.password(), user.getPassword()))
            .thenReturn(true);

        Jwt jwt = Jwt.withTokenValue("token-false")
                .header("alg", "RS256")
                .claim("sub", idUser.toString())
                .build();

        when(jwtEncoder.encode(any(JwtEncoderParameters.class)))
                .thenReturn(jwt);

        LoguinResponse loguinResponse = loguinService.loguin(loguinRequest);
        assertNotNull(loguinResponse);
        assertEquals("username", loguinResponse.username());
        assertEquals("token-false", loguinResponse.jwt());


    }
}