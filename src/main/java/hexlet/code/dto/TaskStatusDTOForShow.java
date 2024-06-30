package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskStatusDTOForShow {

    private Long id;
    private String name;
    private String slug;
    private LocalDate createdAt;
}
