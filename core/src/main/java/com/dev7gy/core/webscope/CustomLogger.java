package com.dev7gy.core.webscope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * http요청당 하나씩 생성될 Bean
 * - Controller와 Service쪽에 ObjectProvider를 적용해서 Bean 생성을 지연하는 방식으로 사용해야 함.
 * - 또는, proxyMode를 설정해주는 방식으로 지원해야 함.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
