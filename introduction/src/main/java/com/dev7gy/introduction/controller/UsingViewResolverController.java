package com.dev7gy.introduction.controller;

import com.dev7gy.introduction.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("viewResolver")
@Controller
public class UsingViewResolverController {

    private DefaultService service;

    @Autowired
    public UsingViewResolverController(DefaultService defaultService) {
        service = defaultService;
    }

    /**
     * 동작 방식
     * 웹브라우저 -> localhost:8080/hello -> 내장 톰캣서버 -> HelloController -> 
     *  public String hello(SpringModel model) -> viewResolver -> templates/hello.html -> 웹 브라우저
     * 
     * @param model
     * @return
     */
    @GetMapping("hello") 
    public String hello(Model model)
    {
        model.addAttribute("data", "hello! introduction");
        return "hello";
        /**
         *  esources/templates/hello.html을 찾아라 란 뜻.
         */
    }

    @GetMapping("mvc")
    public String mvc(@RequestParam(required = false, value = "name") String name, Model model) {
        if (name.isEmpty()) {
            name = "empty";
        }

        model.addAttribute("name", name);
        model.addAttribute("count", service.countByName(name));
        model.addAttribute("modelList", service.selectAll());

        return "mvc";
        /**
         * viewResolver를 통해서 mvc.html 파일을 찾음
         */
    }
}
