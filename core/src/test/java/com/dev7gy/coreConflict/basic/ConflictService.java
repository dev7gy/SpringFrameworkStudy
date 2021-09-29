package com.dev7gy.coreConflict.basic;

import com.dev7gy.coreConflict.ConflictBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConflictService {

    /** 충돌나는 코드
    ConflictBean conflictBean;
    @Autowired
    public ConflictService(ConflictBean conflictBean) {
        this.conflictBeanA = conflictBean;
    }
    */
    ConflictBean conflictBean;
    @Autowired
    public ConflictService(ConflictBean conflictBean) {
        this.conflictBean = conflictBean;
    }
}
