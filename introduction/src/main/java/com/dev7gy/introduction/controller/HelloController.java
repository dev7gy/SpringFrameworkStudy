package com.dev7gy.introduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("mvc")
    public String mvc(@RequestParam(required = false, value = "name") String name, Model model) {
        if (name.isEmpty()) {
            name = "empty";
        }
        model.addAttribute("name", name);
        return "mvc-template";
        /** viewResolver를 통해서 mvc-template.html 파일을 찾음
         *
         */
    }

    @GetMapping("api")
    @ResponseBody
    public Api responseBody(@RequestParam(required = false, value = "name") String name) {
        Api api = new Api();
        api.setName(name);
        return api;
        /** viewResolver 대신 HttpMessageConverter가 동작
         * http://localhost:8080/api?name=test
         * {"name":"test"}
         */
    }

    static class Api {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
