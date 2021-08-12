package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.Model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryModelRepositoryTest {
    MemoryModelRepository repository = new MemoryModelRepository();

    // @AfterEach -> 각 테스트가 종료될 때마다 @AfterEach부분이 실행된다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Model model = new Model();
        model.setName("spring");
        //when
        repository.save(model);
        //then
        Model result = repository.findById(model.getId()).get();
        assertThat(model).isEqualTo(result);
    }
    @Test
    public void findByName() {
        //given
        Model model1 = new Model();
        model1.setName("spring1");
        repository.save(model1);
        Model model2 = new Model();
        model2.setName("spring2");
        repository.save(model2);
        //when
        Model result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(model1);
    }
    @Test
    public void findAll() {
        //given
        Model model1 = new Model();
        model1.setName("spring1");
        repository.save(model1);
        Model model2 = new Model();
        model2.setName("spring2");
        repository.save(model2);
        //when
        List<Model> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
