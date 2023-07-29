package com.phoneshope.java.project.utill;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtill {

    int PAGE_LIMIT=2;
    int DEFAULT_PAGE_NUMBER=1;

    String PAGE_LIMITS = "_limit";
    String PAGE_NUMBER = "_pageSize";
        static Pageable getPageable (int pageNumber, int pageSize) {
        if (pageNumber < DEFAULT_PAGE_NUMBER){
            pageNumber=DEFAULT_PAGE_NUMBER;
        }
        if (pageSize < 1) {
            pageSize=PAGE_LIMIT;
        }
        Pageable pageable= PageRequest.of(pageNumber-1,pageSize);
        return pageable;
    }
}
