package com.himalays.jpaspecfilter.contract;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public interface OperatorAction {

    <V extends Comparable<? super V>, T> Predicate applyCriteria(CriteriaBuilder cb, Path<T> path, Class<V> type, V Value);

}
