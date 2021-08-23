package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.SpringModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataJpaSpringModelRepository extends JpaRepository<SpringModel, Long>, SpringModelRepository {
    Optional<SpringModel> findByName(String name);
}
