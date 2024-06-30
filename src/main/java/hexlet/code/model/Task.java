package hexlet.code.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1)
    private String name;

    private Integer index;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private User assignee;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Label> labels;

    @CreatedDate
    private LocalDateTime createdAt;
}
