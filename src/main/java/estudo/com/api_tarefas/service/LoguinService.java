package estudo.com.api_tarefas.service;

import estudo.com.api_tarefas.database.model.User;
import estudo.com.api_tarefas.database.repository.UserRepository;
import estudo.com.api_tarefas.dto.LoguinRequest;
import estudo.com.api_tarefas.dto.LoguinResponse;
import estudo.com.api_tarefas.exception.InvalidCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LoguinService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    public LoguinService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public LoguinResponse loguin(LoguinRequest request) {

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new InvalidCredentialsException(
                        "invalid username or password"
                ));

        if (!passwordEncoder.matches(request.password(),
                user.getPassword())) {

            throw new InvalidCredentialsException("invalid username or password");
        }

        var roles = user.getRoles()
                .stream()
                .map(role -> role.getRoleName().name())
                .toList();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.getId().toString())
                .claim("roles", roles)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(300))
                .build();

        JwtEncoderParameters parameters =
                JwtEncoderParameters.from(claims);

        String token = jwtEncoder.encode(parameters).getTokenValue();

        return new LoguinResponse(
                user.getUsername(),
                token
        );

    }
}
