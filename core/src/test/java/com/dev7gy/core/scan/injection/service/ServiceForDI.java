package com.dev7gy.core.scan.injection.service;

import com.dev7gy.core.scan.injection.bean.NoSpringBeanForDI;

import java.util.Optional;

public interface ServiceForDI {
    NoSpringBeanForDI getNoSpringBean();
    Optional<NoSpringBeanForDI> getOptionalNoSpringBean();
}
