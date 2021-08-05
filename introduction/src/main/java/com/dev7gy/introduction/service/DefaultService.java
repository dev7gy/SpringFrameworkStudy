package com.dev7gy.introduction.service;

import com.dev7gy.introduction.repository.PsqlModelRepository;
import com.dev7gy.introduction.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultService {

    PsqlModelRepository modelRepository;

    @Autowired
    public DefaultService(PsqlModelRepository psqlModelRepository) {
        modelRepository = psqlModelRepository;
    }

    public Model addModel(String name) {
        Model model = new Model();
        model.setName(name);
        return  modelRepository.save(model);
    }

    public List<Model> selectAll() {
        return modelRepository.findAll();
    }

    public Long countByName(String modelName) {
        return selectAll().stream().filter(m -> m.getName().equals(modelName)).count();
    }
}
