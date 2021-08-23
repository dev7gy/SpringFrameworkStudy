package com.dev7gy.introduction.service;

import com.dev7gy.introduction.model.SpringModel;
import com.dev7gy.introduction.repository.SpringModelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class SpringModelIntegrationTest {
    // 멤버 변수에 직접 @Autowired를 사용하는 방법은 추천하지 않지만 테스트 코드에서는 허용
    @Autowired SpringModelService springModelService;
    @Autowired SpringModelRepository springModelRepository;

    @Test
    public void AddSpringModel() throws Exception {
        //Given - 화면으로 보면 사용자 입력
        SpringModel springModel = new SpringModel();
        springModel.setName("hello0823");

        //When
        Long savedId = springModelService.join(springModel);

        //Then
        SpringModel findModel = springModelRepository.findById(savedId).get();
        assertEquals(springModel.getName(), findModel.getName());
    }
    
    @Test
    public void 중복_Model_예외() throws Exception {
        //Given
        String duplicatedName = "spring0823";
        SpringModel model1 = new SpringModel();
        model1.setName(duplicatedName);
       
        SpringModel model2 = new SpringModel();
        model2.setName(duplicatedName);

        //When
        springModelService.join(model1);
        // 일부러 동일한 이름을 입력
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> springModelService.join(model2));

        //Then
        // 만들어 둔 validateDuplicateSpringModel 함수에 "SpringModel is existed"문장을 던지게 해뒀음.
        assertThat(e.getMessage()).isEqualTo("SpringModel is existed");
    }
}
