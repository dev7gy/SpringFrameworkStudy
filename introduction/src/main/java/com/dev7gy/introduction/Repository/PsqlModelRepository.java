package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.SpringModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsqlModelRepository extends JpaRepository<SpringModel, Long> {
}
