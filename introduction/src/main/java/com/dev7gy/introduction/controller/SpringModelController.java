package com.dev7gy.introduction.controller;

import com.dev7gy.introduction.model.SpringModel;
import com.dev7gy.introduction.model.SpringModelForm;
import com.dev7gy.introduction.service.SpringModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SpringModelController {
    private final SpringModelService springModelService;

    /**
     * 생성자에 @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. - DI 의존성 주입
     *
     * @param springModelService
     * Parameter 0 of constructor in com.dev7gy.introduction.controller.SpringModelController required a bean of type
     * 'com.dev7gy.introduction.service.SpringModelService' that could not be found.
     */
    @Autowired
    public SpringModelController(SpringModelService springModelService) {
        this.springModelService = springModelService;
    }

    @GetMapping(value = "/springModels/new")
    public String createForm() {
        return "springModels/createSpringModelForm";
    }

    @PostMapping(value = "/springModels/new")
    public String create(SpringModelForm form) {
        SpringModel springModel = new SpringModel();
        springModel.setName(form.getName());
        springModelService.join(springModel);
        return "redirect:/";
    }

    @GetMapping(value = "/springModels")
    public String list(Model model) {
        List<SpringModel> members = springModelService.findSpringModels();
        model.addAttribute("springModels", members);
        return "springModels/springModelList";
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