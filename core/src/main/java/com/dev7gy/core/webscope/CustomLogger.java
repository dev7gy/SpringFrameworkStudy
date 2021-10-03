package com.dev7gy.core.webscope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * http요청당 하나씩 생성될 Bean
 */
@Component
@Scope(value = "request")
public class CustomLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("["+uuid+"]" + "["+requestURL+"]" + message);
    }

    @PostConstruct
    public void init()  {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"]" + "request scope bean create:" + this);
    }

    @PreDestroy
    public void close()  {
        System.out.println("["+uuid+"]" + "request scope bean close:" + this);
    }
}
