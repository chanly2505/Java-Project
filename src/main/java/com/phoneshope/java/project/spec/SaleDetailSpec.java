package com.phoneshope.java.project.spec;

import com.phoneshope.java.project.entity.Sale;
import com.phoneshope.java.project.entity.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
@AllArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail> {

    private SaleDetailFilter saleDetailFilter;

    @Override
    public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb ) {
        List<Predicate> predicates = new ArrayList<>();
        Join<SaleDetail, Sale> sale = saleDetail.join("sale");
        if (Objects.nonNull(saleDetailFilter.getStartDate())){
            cb.greaterThanOrEqualTo(sale.get("soldDate"),saleDetailFilter.getStartDate());
        }
        if (Objects.nonNull(saleDetailFilter.getEndDate())){
            cb.lessThanOrEqualTo(sale.get("soldDate"), saleDetailFilter.getEndDate() );
        }
        //Convert List Predicate to Array
        Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
        return predicate;
    }
}