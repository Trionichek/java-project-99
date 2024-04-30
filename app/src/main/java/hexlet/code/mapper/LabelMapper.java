package hexlet.code.mapper;

import hexlet.code.dto.*;
import hexlet.code.model.Label;
import hexlet.code.model.TaskStatus;
import org.mapstruct.*;

@Mapper(
        uses = { JsonNullableMapper.class, ReferenceMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class LabelMapper {

    public abstract Label map(LabelCreateDTO labelCreateDTO);

    public abstract LabelDTO map(Label label);

    public abstract void update(LabelUpdateDTO labelUpdateDTO, @MappingTarget Label label);
}
