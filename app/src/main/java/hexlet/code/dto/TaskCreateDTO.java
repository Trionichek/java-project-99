package hexlet.code.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TaskCreateDTO {
    private Long id;

    @NotBlank
    private String title;

    private Integer index;

    private String content;

    @NotNull
    private String status;

    private Long assignee_id;

    private List<Long> taskLabelIds;

    private Date createdAt;
}
