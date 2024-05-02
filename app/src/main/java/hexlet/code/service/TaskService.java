package hexlet.code.service;


import hexlet.code.dto.TaskCreateDTO;
import hexlet.code.dto.TaskDTO;
import hexlet.code.dto.TaskParamsDTO;
import hexlet.code.dto.TaskUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.model.Task;
import hexlet.code.repository.TaskRepository;
import hexlet.code.specification.TaskSpecification;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskSpecification taskSpecification;

    public List<TaskDTO> getAll(TaskParamsDTO params) {
        Specification<Task> spec = taskSpecification.build(params);
        List<Task> statuses = taskRepository.findAll();
        return statuses.stream()
                .map(t -> taskMapper.map(t))
                .toList();
    }

    public TaskDTO getById(Long id) {
        Task status = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + "not found"));
        return taskMapper.map(status);
    }

    public TaskDTO create(TaskCreateDTO data) {
        Task newStatus = taskMapper.map(data);
        taskRepository.save(newStatus);
        return taskMapper.map(newStatus);
    }

    public TaskDTO update(Long id, TaskUpdateDTO data) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + "not found"));
        taskMapper.update(data, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public void delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + "not found"));

        taskRepository.deleteById(id);
    }
}
