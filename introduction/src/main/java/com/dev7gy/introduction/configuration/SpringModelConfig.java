package com.dev7gy.introduction.configuration;

import com.dev7gy.introduction.repository.MemorySpringModelRepository;
import com.dev7gy.introduction.repository.SpringModelRepository;
import com.dev7gy.introduction.service.SpringModelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 자바 코드로 직접 스프링 빈 등록하기
 */
@Configuration
public class SpringModelConfig {
    @Bean
    public SpringModelService springModelService() {
        return new SpringModelService(springModelRepository());
    }

    @Bean
    public SpringModelRepository springModelRepository() {
        return new MemorySpringModelRepository();
    }
}
