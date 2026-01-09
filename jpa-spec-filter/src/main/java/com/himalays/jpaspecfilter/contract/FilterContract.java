package com.himalays.jpaspecfilter.contract;

import com.himalays.jpaspecfilter.core.FilterCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public interface FilterContract<T> {

    boolean supports(JpaOperator operator);

    Predicate build(
            Path<T> path,
            CriteriaBuilder cb,
            FilterCriteria filter
    );

}
