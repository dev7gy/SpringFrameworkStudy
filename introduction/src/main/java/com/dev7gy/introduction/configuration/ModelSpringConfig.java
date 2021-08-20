package com.dev7gy.introduction.configuration;

import com.dev7gy.introduction.repository.MemoryModelRepository;
import com.dev7gy.introduction.repository.ModelRepository;
import com.dev7gy.introduction.service.ModelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 자바 코드로 직접 스프링 빈 등록하기
 */
@Configuration
public class ModelSpringConfig {
    @Bean
    public ModelService modelService() {
        return new ModelService(modelRepository());
    }

    @Bean
    public ModelRepository modelRepository() {
        return new MemoryModelRepository();
    }
}
