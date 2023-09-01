package com.phoneshope.java.project.spec;

import com.phoneshope.java.project.entity.ProductImportHistory;
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
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {

    private ProductImportHistoryFilter productImportHistoryFilter;

    @Override
    public Predicate toPredicate(Root<ProductImportHistory> productImportHistory, CriteriaQuery<?>  query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        //Join<SaleDetail, Sale> sale = saleDetail.join("sale");
        if (Objects.nonNull(productImportHistoryFilter.getStartDate())){
            cb.greaterThanOrEqualTo(productImportHistory.get("dateImport"), productImportHistoryFilter.getStartDate());
        }
        if (Objects.nonNull(productImportHistoryFilter.getEndDate())){
            cb.lessThanOrEqualTo(productImportHistory.get("dateImport"), productImportHistoryFilter.getEndDate() );
        }
        //Convert List Predicate to Array
        Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
        return predicate;
    }
}