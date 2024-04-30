package hexlet.code.service;

import hexlet.code.dto.*;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.LabelMapper;
import hexlet.code.model.Label;
import hexlet.code.repository.LabelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelMapper labelMapper;

    public List<LabelDTO> getAll() {
        List<Label> statuses = labelRepository.findAll();
        return statuses.stream()
                .map(t -> labelMapper.map(t))
                .toList();
    }

    public LabelDTO getById(Long id) {
        Label status = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + "not found"));
        return labelMapper.map(status);
    }

    public LabelDTO create(LabelCreateDTO data) {
        Label newStatus = labelMapper.map(data);
        labelRepository.save(newStatus);
        return labelMapper.map(newStatus);
    }

    public LabelDTO update(Long id, LabelUpdateDTO data) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + "not found"));
        labelMapper.update(data, label);
        labelRepository.save(label);
        return labelMapper.map(label);
    }

    public void delete(Long id) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + "not found"));

        labelRepository.deleteById(id);
    }
}
