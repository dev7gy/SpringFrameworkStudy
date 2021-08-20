package com.dev7gy.introduction.service;

import com.dev7gy.introduction.model.SpringModel;
import com.dev7gy.introduction.repository.PsqlModelRepository;
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

    public SpringModel addModel(String name) {
        SpringModel springModel = new SpringModel();
        springModel.setName(name);
        return  modelRepository.save(springModel);
    }

    public List<SpringModel> selectAll() {
        return modelRepository.findAll();
    }

    public Long countByName(String modelName) {
        return selectAll().stream().filter(m -> m.getName().equals(modelName)).count();
    }
}
