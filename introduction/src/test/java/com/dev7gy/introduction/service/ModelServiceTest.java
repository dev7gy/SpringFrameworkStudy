package com.dev7gy.introduction.service;

import com.dev7gy.introduction.model.Model;
import com.dev7gy.introduction.repository.MemoryModelRepository;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ModelServiceTest {
    ModelService modelService;
    MemoryModelRepository modelRepository;

    @BeforeEach // 각 테스트 실행 전에 호출됨.
    public void beforeEach() {
        modelRepository = new MemoryModelRepository();
        modelService = new ModelService(modelRepository);
    }

    @AfterEach
    public void afterEach() {
        modelRepository.clearStore();
    }

    @Test
    public void Model_추가() throws Exception {
        // Given
        Model model = new Model();
        model.setName("hello");

        // When
        Long addedId = modelService.join(model);

        // Then
        Model findModel = modelRepository
                .findById(addedId)
                .get();

        assertEquals(model.getName(), findModel.getName());
    }
    @Test
    public void 중복_Model_예외처리() throws Exception {
        // Given
        Model model1 = new Model();
        model1.setName("spring");

        Model model2 = new Model();
        model2.setName("spring");

        // When
        modelService.join(model1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> modelService.join(model2));

        assertThat(e.getMessage())
                .isEqualTo("Name spring is Exsited");
    }
}
