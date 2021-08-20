package com.dev7gy.introduction.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class DefaultServiceTest {
    @Autowired  DefaultService defaultService;
    //  @Autowired JdbcSpringModelRepository psqlModelRepository;

    @Test
    public void Model추가() throws Exception {

        // Given
        String modelName = "testModel01";
        defaultService.addModel(modelName);

        // When

        // Then
        assertEquals(defaultService.countByName(modelName), 1);
        //List<SpringModel> modelList = defaultService.selectAll();
        //assertEquals(modelList.)
    }
}
