package estudo.com.api_tarefas.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username
) {
}
