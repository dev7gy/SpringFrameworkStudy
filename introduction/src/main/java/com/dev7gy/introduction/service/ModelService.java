package com.dev7gy.introduction.service;

import com.dev7gy.introduction.model.Model;
import com.dev7gy.introduction.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class ModelService {
    /*
    private final ModelRepository repository = new MemoryModelRepository();
    --> 생성자를 통해 DI 해주는 방식으로 변경
     */
    private final ModelRepository repository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.repository = modelRepository;
    }
    
    // 메소드
    public Long join(Model model) {
        repository.save(model);
        return model.getId();
    }

    private void validateDuplicateModel(Model model) {
        repository.findByName(model.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("Model is existed");
                });
    }

    public List<Model> findModels() {
        return repository.findAll();
    }

    public Optional<Model> findById(Long modelId) {
        return repository.findById(modelId);
    }

}
