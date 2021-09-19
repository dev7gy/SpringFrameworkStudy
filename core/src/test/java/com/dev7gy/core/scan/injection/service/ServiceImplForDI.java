package com.dev7gy.core.scan.injection.service;

import com.dev7gy.core.scan.injection.bean.NoSpringBeanForDI;
import com.dev7gy.core.scan.injection.bean.SpringBeanForDI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceImplForDI implements ServiceForDI {
    SpringBeanForDI springBeanForDI;
    NoSpringBeanForDI noSpringBeanForDI;

    /*
    @Autowired
    public ServiceImplForDI(SpringBeanForDI springBeanForDI, NoSpringBeanForDI noSpringBeanForDI) {
        this.springBeanForDI = springBeanForDI;
    }
    */

    // NoSpringBeanForDI가 스프링빈이 아니기 떄문에 호출되지 않음.
    @Autowired(required = false)
    public void setNoSpringBean_1(NoSpringBeanForDI noSpringBeanForDI) {
        System.out.println("Autowired(required = false)");
        System.out.println("noSpringBeanForDI = " + noSpringBeanForDI);
        this.noSpringBeanForDI = noSpringBeanForDI;
    }

    // 자동 주입할 대상이 없으면 null 호출
    @Autowired
    public void setNoSpringBean_2(@Nullable NoSpringBeanForDI noSpringBeanForDI) {
        System.out.println("@Nullable");
        System.out.println("noSpringBeanForDI = " + noSpringBeanForDI);
        this.noSpringBeanForDI = noSpringBeanForDI;
    }

    // 자동 주입할 대상이 없으면 Optional.empty 호출
    @Autowired(required = false)
    public void setNoSpringBean_3(Optional<NoSpringBeanForDI> noSpringBeanForDI) {
        System.out.println("Optinal<>");
        System.out.println("noSpringBeanForDI = " + noSpringBeanForDI);
        this.noSpringBeanForDI = noSpringBeanForDI.get();
    }

    @Override
    public NoSpringBeanForDI getNoSpringBean() {
        return this.noSpringBeanForDI;
    }
}
