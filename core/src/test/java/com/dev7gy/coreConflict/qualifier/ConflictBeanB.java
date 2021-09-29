package com.dev7gy.coreConflict.qualifier;

import com.dev7gy.coreConflict.ConflictBean;
import com.dev7gy.coreConflict.annotation.ConflictBeanAnnotation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@ConflictBeanAnnotation
public class ConflictBeanB implements ConflictBean {
}
