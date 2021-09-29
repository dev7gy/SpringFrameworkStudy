package com.dev7gy.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
/**
 * excludeFilters 를 안해주면 AppConfig 클래스에도 @Configuration 이 붙어 있기 때문에 빈으로 등록한다.
 * Name = autoAppConfigObject = com.dev7gy.core.AutoAppConfig$$EnhancerBySpringCGLIB$$8500e8be@1cf0cacc
 * Name = appConfigObject = com.dev7gy.core.AppConfig$$EnhancerBySpringCGLIB$$6901db8d@4f5b08d
 */
@ComponentScan(
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = Configuration.class),
        }
)
public class AutoAppConfig {
}
