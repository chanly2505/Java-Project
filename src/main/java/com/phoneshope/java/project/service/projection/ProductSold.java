package com.phoneshope.java.project.service.projection;

import java.math.BigDecimal;

public interface ProductSold {

    Long getId();

    String getProduct_Name();

    Integer getUnit();

    BigDecimal getTotalAmount();
}
