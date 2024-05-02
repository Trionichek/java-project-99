package hexlet.code.mapper;

import hexlet.code.dto.TaskCreateDTO;
import hexlet.code.dto.TaskDTO;
import hexlet.code.dto.TaskUpdateDTO;
import hexlet.code.model.Task;
import hexlet.code.repository.LabelRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(
        uses = { JsonNullableMapper.class, ReferenceMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {

    @Autowired
    private LabelRepository labelRepository;

    @Mapping(target = "name", source = "title")
    @Mapping(target = "description", source = "content")
/*    @Mapping(target = "assignee", source = "assignee_id")
    @Mapping(target = "taskStatus.slug", source = "status")
    @Mapping(target = "taskStatus.name", defaultValue = "HuiSosi")*/
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "labels", ignore = true)
    @Mapping(target = "taskStatus", ignore = true)
    public abstract Task map(TaskCreateDTO taskCreateDTO);

    @Mapping(source = "name", target = "title")
    @Mapping(source = "description", target = "content")
    @Mapping(source = "taskStatus.slug", target = "status")
    @Mapping(source = "assignee.id", target = "assigneeId")
    //@Mapping(source = "labels", target = "taskLabelIds")
    public abstract TaskDTO map(Task task);

    @Mapping(source = "title", target = "name")
    @Mapping(source = "content", target = "description")
    @Mapping(source = "status", target = "taskStatus.slug")
    @Mapping(source = "assigneeId", target = "assignee")
    public abstract void update(TaskUpdateDTO dto, @MappingTarget Task task);

   /* public Set<Label> toLabelSet(List<Long> ids) {
        return new HashSet<>(labelRepository.findAllById(ids));
    }

    public List<Long> toLabelIdList(Set<Label> labels) {
        return labels.stream()
                .map(Label::getId)
                .toList();
    }*/
}
