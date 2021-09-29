package com.dev7gy.coreConflict.primary;

import com.dev7gy.coreConflict.ConflictBean;
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
