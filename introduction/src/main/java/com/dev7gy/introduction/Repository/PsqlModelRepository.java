package com.dev7gy.introduction.Repository;

import com.dev7gy.introduction.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsqlModelRepository extends JpaRepository<Model, Long> {
}
