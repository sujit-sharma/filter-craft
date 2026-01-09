package com.himalays.jpaspecfilter.core;

import com.himalays.jpaspecfilter.contract.JpaOperator;

public record FilterCriteria(
        String field,
        JpaOperator operator,
        Object value
) {
}
