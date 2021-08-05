package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    Model save(Model model);
    Optional<Model> findById(Long id);
    Optional<Model> findByName(String name);
    List<Model> findAll();
}
