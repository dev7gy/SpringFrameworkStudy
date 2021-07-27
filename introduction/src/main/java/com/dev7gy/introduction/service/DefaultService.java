package com.dev7gy.introduction.service;

import com.dev7gy.introduction.Repository.PsqlModelRepository;
import com.dev7gy.introduction.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Long countByKey(String key) {
        return selectAll().stream().filter(m -> m.equals(key)).count();
    }
}
