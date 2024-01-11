package com.phoneshope.java.project.projection;

import java.math.BigDecimal;

public interface ProductSold {

    Long getId();

    String getProduct_Name();

    Integer getUnit();

    BigDecimal getTotalAmount();
}
