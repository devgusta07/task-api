package estudo.com.api_tarefas.controller;

import estudo.com.api_tarefas.dto.LoguinRequest;
import estudo.com.api_tarefas.dto.LoguinResponse;
import estudo.com.api_tarefas.service.LoguinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loguin")
public class LoguinController {

    private final LoguinService loguinService;
    public LoguinController(LoguinService loguinService) {
        this.loguinService = loguinService;
    }

    @PostMapping
    public ResponseEntity<LoguinResponse> createUser(@RequestBody LoguinRequest loguinRequest) {

        return ResponseEntity.ok().body(loguinService.loguin(loguinRequest));
    }
}
