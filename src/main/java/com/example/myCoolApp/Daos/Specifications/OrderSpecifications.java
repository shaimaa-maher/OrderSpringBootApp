package com.example.myCoolApp.Daos.Specifications;

import com.example.myCoolApp.Entities.Order;
import com.example.myCoolApp.Entities.Order_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class OrderSpecifications {

    public static Specification<Order> filterOrders(String customerName, Date orderDate, Double totalAmount){
        return (Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.like((root.get(Order_.customerName)),"%"+customerName+"%"));
            predicates.add(cb.greaterThanOrEqualTo(root.get(Order_.orderDate), orderDate));
            predicates.add(cb.lessThanOrEqualTo(root.get(Order_.totalAmount), totalAmount));

            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
