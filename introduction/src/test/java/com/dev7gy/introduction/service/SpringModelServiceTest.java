package com.dev7gy.introduction.service;

import com.dev7gy.introduction.model.SpringModel;
import com.dev7gy.introduction.repository.MemorySpringModelRepository;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SpringModelServiceTest {
    SpringModelService springModelService;
    MemorySpringModelRepository modelRepository;

    @BeforeEach // 각 테스트 실행 전에 호출됨.
    public void beforeEach() {
        modelRepository = new MemorySpringModelRepository();
        springModelService = new SpringModelService(modelRepository);
    }

    @AfterEach
    public void afterEach() {
        modelRepository.clearStore();
    }

    @Test
    public void Model_추가() throws Exception {
        // Given
        SpringModel springModel = new SpringModel();
        springModel.setName("hello");

        // When
        Long addedId = springModelService.join(springModel);

        // Then
        SpringModel findSpringModel = modelRepository
                .findById(addedId)
                .get();

        assertEquals(springModel.getName(), findSpringModel.getName());
    }
    @Test
    public void 중복_Model_예외처리() throws Exception {
        // Given
        SpringModel springModel1 = new SpringModel();
        springModel1.setName("spring");

        SpringModel springModel2 = new SpringModel();
        springModel2.setName("spring");

        // When
        springModelService.join(springModel1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> springModelService.join(springModel2));

        assertThat(e.getMessage())
                .isEqualTo("Name spring is Exsited");
    }
}
