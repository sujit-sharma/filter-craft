package com.himalays.jpaspecfilter.core;

import com.himalays.jpaspecfilter.contract.FilterContract;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public abstract class AbstractFilterHandler<T> implements FilterContract<T> {

    protected abstract T convert(Object value);


    @Override
    public Predicate build(Path<T> path, CriteriaBuilder cb, FilterCriteria filter) {
        return null;
    }
}
