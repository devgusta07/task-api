package estudo.com.api_tarefas.infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class RestError {

    private HttpStatus status;
    private String message;
}
