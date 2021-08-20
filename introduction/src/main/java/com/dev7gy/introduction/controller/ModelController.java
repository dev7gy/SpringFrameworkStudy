package com.dev7gy.introduction.controller;

import com.dev7gy.introduction.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ModelController {
    private final ModelService modelService;

    /**
     * 생성자에 @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. - DI 의존성 주입
     *
     * @param modelService
     * Parameter 0 of constructor in com.dev7gy.introduction.controller.ModelController required a bean of type
     * 'com.dev7gy.introduction.service.ModelService' that could not be found.
     */
    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }
}
/**
 * 중요! 스프링 빈을 등록하는 2가지 방법
 * 컴포넌트 스캔과 자동 의존관계 설정
 * -> @Component 어노테이션이 있으면 스프링 빈을 자동으로 등록된다. - @Controller, @Service, @Repository도 @Component의 한 종류
 *  - 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다.
 * 
 * 자바 코드로 직접 스프링 빈 등록하기
 * 
 */