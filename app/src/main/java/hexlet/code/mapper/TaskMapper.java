package hexlet.code.mapper;

import hexlet.code.dto.TaskCreateDTO;
import hexlet.code.dto.TaskDTO;
import hexlet.code.dto.TaskUpdateDTO;
import hexlet.code.model.Label;
import hexlet.code.model.Task;
import hexlet.code.repository.TaskStatusRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        uses = { JsonNullableMapper.class, ReferenceMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {

    @Mapping(target = "name", source = "title")
    @Mapping(target = "description", source = "content")
    @Mapping(target = "assignee", source = "assignee_id")
    @Mapping(target = "taskStatus.slug", source = "status")
    public abstract Task map(TaskCreateDTO taskCreateDTO);

    @Mapping(source = "name", target = "title")
    @Mapping(source = "description", target = "content")
    @Mapping(source = "taskStatus.slug", target = "status")
    @Mapping(source = "assignee.id", target = "assignee_id")
    @Mapping(target = "taskLabelIds", expression = "java(labelsToLabelIds(task.getLabels()))")
    public abstract TaskDTO map(Task task);

    @Mapping(source = "title", target = "name")
    @Mapping(source = "content", target = "description")
    @Mapping(source = "status", target = "taskStatus.slug")
    @Mapping(source = "assignee_id", target = "assignee")
    public abstract void update(TaskUpdateDTO dto, @MappingTarget Task task);

    public List<Long> labelsToLabelIds(List<Label> labels) {
        return labels.stream()
                .map(Label::getId)
                .toList();
    }
}
