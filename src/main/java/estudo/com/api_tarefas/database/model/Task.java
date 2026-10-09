package estudo.com.api_tarefas.database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_task")
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
