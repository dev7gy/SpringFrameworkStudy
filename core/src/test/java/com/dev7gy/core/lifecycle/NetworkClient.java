package com.dev7gy.core.lifecycle;

/**
 * 실제 네트워크 연결이 아닌 네트워크 연결 시나리오용 예제
 * 핵심은 해당 예제를 통해 Spring Bean 생명주기 콜백을 파악하는 것
 */
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("Constructor, url = " + url);
        connect();
        call("Init Connect Call");
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
