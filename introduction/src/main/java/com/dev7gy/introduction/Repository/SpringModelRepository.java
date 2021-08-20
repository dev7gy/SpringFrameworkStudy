package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.SpringModel;

import java.util.List;
import java.util.Optional;

public interface SpringModelRepository {
    SpringModel save(SpringModel springModel);
    Optional<SpringModel> findById(Long id);
    Optional<SpringModel> findByName(String name);
    List<SpringModel> findAll();
}
