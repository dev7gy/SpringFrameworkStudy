package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.SpringModel;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class JpaSpringModelRepository implements SpringModelRepository {

    // jpa는 entity manager를 data jpa에서 알아서 만들어줌.
    private final EntityManager entityManager;

    public JpaSpringModelRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public SpringModel save(SpringModel springModel) {
        entityManager.persist(springModel);
        return springModel;
    }

    @Override
    public Optional<SpringModel> findById(Long id) {
        // pk만 find함수로 사용 가능
        SpringModel springModel = entityManager.find(SpringModel.class, id);
        return Optional.ofNullable(springModel);
    }

    @Override
    public Optional<SpringModel> findByName(String name) {
       // jpql
        List<SpringModel> result = entityManager
                .createQuery("select m from SpringModel m where m.name = :name", SpringModel.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<SpringModel> findAll() {
        // jpql
        return entityManager.createQuery("select m from SpringModel m", SpringModel.class).getResultList();
    }
}
