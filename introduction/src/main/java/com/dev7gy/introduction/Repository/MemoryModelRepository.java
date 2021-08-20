package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.Model;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryModelRepository implements ModelRepository {
    private static Map<Long, Model> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Model save(Model model) {
        model.setId(++sequence);
        store.put(model.getId(), model);
        return model;
    }

    @Override
    public Optional<Model> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Model> findByName(String name) {
        return store.values().stream()
                .filter(model -> model.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Model> findAll() {
        Collection<Model> values = store.values();

        return new ArrayList<Model>(values);
    }

    public void clearStore() {
        store.clear();
    }
}
