package com.dev7gy.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 실제 네트워크 연결이 아닌 네트워크 연결 시나리오용 예제
 * 핵심은 해당 예제를 통해 Spring Bean 생명주기 콜백을 파악하는 것
 */
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("Constructor, url = " + url);
        /* 대개 실제 Connect는 오래 걸리는 작업이므로 객체 생성과 작업을 분리하는 것이 좋다.
        connect();
        call("Init Connect Call");
         */
    }

    /**
     * init()함수와 close()함수를 @Configuration에 @Bean등록시 명시함.
     */
    public void init() {
        System.out.println("NetworkClient.init()");
        connect();
        call("Connect Init");
    }

    public void close() {
        System.out.println("NetworkClient.close()");
        disconnect();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }
    
    public void call(String message) {
        System.out.println("call" + url + "message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }
}
