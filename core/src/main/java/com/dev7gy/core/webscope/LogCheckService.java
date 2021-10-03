package com.dev7gy.core.webscope;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogCheckService {
    private final ObjectProvider<CustomLogger> customLoggerProvider;

    public void logic(String checkId) {
        CustomLogger customLogger = customLoggerProvider.getObject();
        customLogger.log("service ID = " + checkId);
    }
}
