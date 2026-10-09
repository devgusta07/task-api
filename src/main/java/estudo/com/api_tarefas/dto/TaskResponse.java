package estudo.com.api_tarefas.dto;

import java.util.Date;
import java.util.UUID;

public record TaskResponse(UUID id,String name, Date date, UUID id_user) {

}
