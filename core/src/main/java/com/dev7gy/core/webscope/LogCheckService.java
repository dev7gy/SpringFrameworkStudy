package com.dev7gy.core.webscope;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogCheckService {
    private final CustomLogger customLogger;

    public void logic(String checkId) {
        customLogger.log("service ID = " + checkId);
    }
}
