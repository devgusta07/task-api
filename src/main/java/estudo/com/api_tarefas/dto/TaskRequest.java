package estudo.com.api_tarefas.dto;

import java.util.Date;
import java.util.UUID;

public record TaskRequest(String name, Date date) {
}
