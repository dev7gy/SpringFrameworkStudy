package com.dev7gy.core.webscope;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogCheckController {
    private final LogCheckService logCheckService;
    private final CustomLogger customLogger;

    @RequestMapping("log-check")
    @ResponseBody
    public String logCheck(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        customLogger.setRequestURL(requestURL);

        customLogger.log("controller check");
        logCheckService.logic("checkId");
        return "OK";
    }
}
