package com.dev7gy.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class ProtoTypeTest {
    @Test
    public void FindProtoTypeBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        System.out.println("get protoTypeBean1");
        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("get protoTypeBean2");
        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);
        System.out.println("protoTypeBean1 = " + protoTypeBean1);
        System.out.println("protoTypeBean2 = " + protoTypeBean2);
        assertThat(protoTypeBean1).isNotSameAs(protoTypeBean2);
        ac.close(); // protoType은 스프링컨테이너를 close해도 관리대상이 아니기 때문에 @PreDestroy를 호출하지 않음.
    }

    @Scope("prototype")
    static class ProtoTypeBean {
        @PostConstruct
        public void init() {
            System.out.println("ProtoType Bean - init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("ProtoType Bean - destroy");
        }
    }
}
