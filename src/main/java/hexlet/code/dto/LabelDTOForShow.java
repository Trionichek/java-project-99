package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LabelDTOForShow {

    private Long id;

    private String name;

    private LocalDate createdAt;
}
