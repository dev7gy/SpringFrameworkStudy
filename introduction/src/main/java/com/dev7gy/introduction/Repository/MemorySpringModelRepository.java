package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.SpringModel;

import java.util.*;

public class MemorySpringModelRepository implements SpringModelRepository {
    private static Map<Long, SpringModel> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public SpringModel save(SpringModel springModel) {
        springModel.setId(++sequence);
        store.put(springModel.getId(), springModel);
        return springModel;
    }

    @Override
    public Optional<SpringModel> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<SpringModel> findByName(String name) {
        return store.values().stream()
                .filter(model -> model.getName().equals(name))
                .findAny();
    }

    @Override
    public List<SpringModel> findAll() {
        Collection<SpringModel> values = store.values();

        return new ArrayList<SpringModel>(values);
    }

    public void clearStore() {
        store.clear();
    }
}
