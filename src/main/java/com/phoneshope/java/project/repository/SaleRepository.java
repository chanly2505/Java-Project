package com.phoneshope.java.project.repository;

import com.phoneshope.java.project.entity.Sale;
import com.phoneshope.java.project.service.projection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    @Query(value = "SELECT p.id, p.product_name as product_name , sum(sd.unit)unit, sum(sd.unit * sd.amount)totalAmount FROM sales_details sd \n" +
            "            INNER JOIN sales s on sd.sale_id = s.sale_id \n" +
            "            INNER join products p on p.id = sd.product_id\n" +
            "            WHERE date(s.sold_date) >= :startDate and date(s.sold_date) <= :endDate \n" +
            "            GROUP by p.id , p.product_name" , nativeQuery = true)
    List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
}

