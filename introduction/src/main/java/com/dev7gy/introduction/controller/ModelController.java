package com.dev7gy.introduction.controller;

import com.dev7gy.introduction.model.Model;
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
