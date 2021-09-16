package com.dev7gy.core.scan.conflict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConflictService {

    ConflictBean conflictBean;
    @Autowired
    public ConflictService(ConflictBean conflictBean) {
        this.conflictBean = conflictBean;
    }
}
