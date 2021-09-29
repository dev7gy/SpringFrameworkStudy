package com.dev7gy.coreConflict.qualifier;

import com.dev7gy.coreConflict.ConflictBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("main")
public class ConflictBeanA implements ConflictBean {
}
