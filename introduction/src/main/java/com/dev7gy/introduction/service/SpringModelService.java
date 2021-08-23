package com.dev7gy.introduction.service;

import com.dev7gy.introduction.model.SpringModel;
import com.dev7gy.introduction.repository.SpringModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// !중요 jpa 사용하면서 사용 - jpa를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
@Transactional
public class SpringModelService {
    /*
    private final SpringModelRepository repository = new MemorySpringModelRepository();
    --> 생성자를 통해 DI 해주는 방식으로 변경
     */
    private final SpringModelRepository repository;

    @Autowired
    public SpringModelService(SpringModelRepository springModelRepository) {
        this.repository = springModelRepository;
    }

    private void printTimeMessage(long startTime, String functionName) {
        long finish = System.currentTimeMillis();
        long timeMs = finish - startTime;
        System.out.println(functionName + " " + timeMs + "ms");
    }

    // 메소드
    public Long join(SpringModel springModel) {
        validateDuplicateSpringModel(springModel);
        repository.save(springModel);
        return springModel.getId();
    }

    private void validateDuplicateSpringModel(SpringModel springModel) {
        repository.findByName(springModel.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("SpringModel is existed");
                });
    }

    public List<SpringModel> findSpringModels() {
        return repository.findAll();
    }

    public Optional<SpringModel> findById(Long springModelId) {
        return repository.findById(springModelId);
    }

}
