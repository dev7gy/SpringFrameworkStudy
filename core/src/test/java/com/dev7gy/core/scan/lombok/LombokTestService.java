package com.dev7gy.core.scan.lombok;

import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class LombokTestService {
    private String name;
}
