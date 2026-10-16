package estudo.com.api_tarefas.controller;

import estudo.com.api_tarefas.dto.UserResponse;
import estudo.com.api_tarefas.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/seacher")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId) {

        return ResponseEntity.ok(adminService.SearchUser(userId));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
