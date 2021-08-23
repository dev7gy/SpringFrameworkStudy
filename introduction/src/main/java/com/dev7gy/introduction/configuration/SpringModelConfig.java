package com.dev7gy.introduction.configuration;

import com.dev7gy.introduction.repository.*;
import com.dev7gy.introduction.service.SpringModelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 * 자바 코드로 직접 스프링 빈 등록하기
 */
@Configuration
public class SpringModelConfig {
    /*
    JDBC
    JDBC 템플릿용
    private final DataSource dataSource;
    public SpringModelConfig(DataSource dataSource) {
        this.dataSource = dataSource;
        this.entityManager = null;
    }
    */

    /*
    JPA용

    @PersistenceContext
    private final EntityManager entityManager;

    public SpringModelConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    */

    /*
    SpringDataJPA 사용
     */
    private final SpringModelRepository springModelRepository;
    public SpringModelConfig(SpringModelRepository springModelRepository) {
        this.springModelRepository = springModelRepository;
    }
    @Bean
    public SpringModelService springModelService() {
        /*
        // SpringDataJPA 사용 전
        return new SpringModelService(springModelRepository());
        */
        /*
        SpringDataJPA
         */
        return new SpringModelService(this.springModelRepository);

    }

    /*
    @Bean
    public SpringModelRepository springModelRepository() {
        // return new MemorySpringModelRepository(); // memory DB
        //return new JdbcSpringModelRepository(dataSource); // jdbc를 직접 사용
        // return new JdbcTemplateSpringModelRepository(dataSource); // jbbc 템플릿 사용
        // return new JpaSpringModelRepository(this.entityManager); // jpa 사용
    }
     */
}
