package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.SpringModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemorySpringModelRepositoryTest {
    MemorySpringModelRepository repository = new MemorySpringModelRepository();

    // @AfterEach -> 각 테스트가 종료될 때마다 @AfterEach부분이 실행된다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        SpringModel springModel = new SpringModel();
        springModel.setName("spring");
        //when
        repository.save(springModel);
        //then
        SpringModel result = repository.findById(springModel.getId()).get();
        assertThat(springModel).isEqualTo(result);
    }
    @Test
    public void findByName() {
        //given
        SpringModel springModel1 = new SpringModel();
        springModel1.setName("spring1");
        repository.save(springModel1);
        SpringModel springModel2 = new SpringModel();
        springModel2.setName("spring2");
        repository.save(springModel2);
        //when
        SpringModel result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(springModel1);
    }
    @Test
    public void findAll() {
        //given
        SpringModel springModel1 = new SpringModel();
        springModel1.setName("spring1");
        repository.save(springModel1);
        SpringModel springModel2 = new SpringModel();
        springModel2.setName("spring2");
        repository.save(springModel2);
        //when
        List<SpringModel> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
