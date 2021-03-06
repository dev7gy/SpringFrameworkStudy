package com.dev7gy.introduction.service;

import com.dev7gy.introduction.model.SpringModel;
import com.dev7gy.introduction.repository.JdbcSpringModelRepository;
import com.dev7gy.introduction.repository.SpringModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultService {

    SpringModelRepository springModelRepository;

    @Autowired
    public DefaultService(SpringModelRepository springModelRepository) {
        this.springModelRepository = springModelRepository;
    }

    public SpringModel addModel(String name) {
        SpringModel springModel = new SpringModel();
        springModel.setName(name);
        return  springModelRepository.save(springModel);
    }

    public List<SpringModel> selectAll() {
        return springModelRepository.findAll();
    }

    public Long countByName(String modelName) {
        return selectAll().stream().filter(m -> m.getName().equals(modelName)).count();
    }
}
