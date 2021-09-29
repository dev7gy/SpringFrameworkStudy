package com.dev7gy.coreConflict.allBeanDi;

import com.dev7gy.coreConflict.ConflictBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConflictService {

    /** 충돌나는 코드
    ConflictBean conflictBean;
    @Autowired
    public ConflictService(ConflictBean conflictBean) {
        this.conflictBeanA = conflictBean;
    }
    */
    private Map<String, ConflictBean> beanMap;
    private List<ConflictBean> beanList;
    @Autowired
    public ConflictService(Map<String, ConflictBean> conflictBeanMap, List<ConflictBean> conflictBeanList) {
        beanMap = conflictBeanMap;
        beanList = conflictBeanList;
        System.out.println(conflictBeanMap);
        System.out.println(conflictBeanList);
    }

    public ConflictBean getConflictBean(String beanName) {
        return beanMap.get(beanName);
    }
}
