package com.dev7gy.introduction.controller;

import com.dev7gy.introduction.model.SpringModel;
import com.dev7gy.introduction.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("responsebody")
@Controller
public class WithoutViewResolverController {

    private DefaultService service;

    @Autowired
    WithoutViewResolverController (DefaultService defaultService){
        service = defaultService;
    }

    @GetMapping("model")
    @ResponseBody
    public SpringModel responseBody(@RequestParam(required = false, value = "name") String name) {

        return service.addModel(name);
        /** viewResolver 대신 HttpMessageConverter가 동작
         * http://localhost:8080/api?name=test
         * return: {"name":"test"}
         */
    }

    @GetMapping("string")
    @ResponseBody
    public String responseBodyString(@RequestParam(required = false, value = "name") String name) {
        return name;
        /** viewResolver 대신 HttpMessageConverter가 동작
         * http://localhost:8080/api?name=test
         * return: "test"
         */
    }
}
