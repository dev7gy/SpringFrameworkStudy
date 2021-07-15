package com.dev7gy.introduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    /**
     * 동작 방식
     * 웹브라우저 -> localhost:8080/hello -> 내장 톰캣서버 -> HelloController -> 
     *  public String hello(Model model) -> viewResolver -> templates/hello.html -> 웹 브라우저 
     * 
     * @param model
     * @return
     */
    @GetMapping("hello") 
    public String hello(Model model)
    {
        model.addAttribute("data", "hello! introduction");
        return "hello";
    }
}
