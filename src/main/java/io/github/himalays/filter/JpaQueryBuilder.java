package io.github.himalays.filter;

import jakarta.persistence.LockModeType;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static io.github.himalays.filter.JpaOperator.IS_NULL;
import static io.github.himalays.filter.JpaQuery.query;
import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class JpaQueryBuilder<T> {

    private final Function<JpaQuery, List<T>> function;

    private int max;
    private int position;
    private String selectStatement = "";
    private String whereClause = "";
    private List<JpaFilter> filters = new ArrayList<>();
    private LockModeType lockModeType;
    private final Map<String, Object> hints = new HashMap<>();

    public JpaQueryBuilder(Function<JpaQuery, List<T>> function) {
        this.function = function;
    }

    public JpaQueryBuilder<T> selectStatement(String selectStatement) {
        this.selectStatement = selectStatement != null && !selectStatement.isBlank() ? selectStatement : "";
        return this;
    }

    public JpaQueryBuilder<T> filter(String property, JpaOperator operator, Object value) {
        return filter(buildFilter(property, operator, value));
    }

    public JpaQueryBuilder<T> filter(String property, JpaOperator operator) {
        return filter(JpaFilter.filter(property, operator));
    }

    public JpaQueryBuilder<T> filterIf(boolean condition, JpaFilter filter) {
        if (condition)
            filter(filter);
        return this;
    }

    public JpaQueryBuilder<T> filterIf(boolean condition, String property, JpaOperator operator, Object value) {
        return filterIf(condition, buildFilter(property, operator, value));
    }

    public JpaQueryBuilder<T> filter(JpaFilter filter) {
        filters.add(requireNonNull(filter, "filter is null"));
        return this;
    }


    public JpaQuery build() {
        return query(selectStatement, whereClause(), parameters());
    }

    private List<JpaParameter> parameters() {
        return ofNullable(filters).map(this::parameters).orElse(emptyList());
    }

    private List<JpaParameter> parameters(List<JpaFilter> list) {
        return IntStream.range(0, list.size()).boxed()
                .map(i -> list.get(i).toParameter("p" + i))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public JpaQueryBuilder<T> whereClause(String whereClause) {
        this.whereClause = whereClause != null && !whereClause.isBlank() ? whereClause : "";
        return this;
    }

    private String whereClause() {
        return ofNullable(filters).map(this::whereClause).orElse(whereClause);
    }

    private String whereClause(List<JpaFilter> list) {
        StringBuilder where = new StringBuilder(this.whereClause);
        for (int i = 0; i < list.size(); i++) {
            if (!where.toString().isBlank())
                where.append(" AND ");
            where.append(list.get(i).toWhere("p" + i));
        }
        return where.toString();
    }


    private JpaFilter buildFilter(String property, JpaOperator operator, Object value) {
        return value == null ? JpaFilter.filter(property, IS_NULL) : JpaFilter.filter(property, operator, value);
    }
}
