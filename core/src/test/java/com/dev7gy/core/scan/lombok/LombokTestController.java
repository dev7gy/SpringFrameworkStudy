package com.dev7gy.core.scan.lombok;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@Getter
@RequiredArgsConstructor
public class LombokTestController {
    private final LombokTestService lombokTestService;
}
