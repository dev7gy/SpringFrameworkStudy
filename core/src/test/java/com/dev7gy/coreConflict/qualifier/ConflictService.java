package com.dev7gy.coreConflict.qualifier;

import com.dev7gy.coreConflict.ConflictBean;
import com.dev7gy.coreConflict.annotation.ConflictBeanAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConflictService {

    ConflictBean conflictBean;
    @Autowired
    /*
    public ConflictService(@Qualifier("main") ConflictBean conflictBean) {
        this.conflictBean = conflictBean;
    }
     */

    // @Qualifire 대신에 Annotaion 만들어서 적용하기
    public ConflictService(@ConflictBeanAnnotation ConflictBean conflictBean) {
        this.conflictBean = conflictBean;
    }
}
