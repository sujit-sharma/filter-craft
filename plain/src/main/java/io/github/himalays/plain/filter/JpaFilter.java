package io.github.himalays.plain.filter;


import java.util.Objects;
import java.util.Optional;

import static io.github.himalays.plain.filter.JpaParameter.parameter;
import static java.util.Objects.isNull;

public class JpaFilter {

    private final String property;
    private final Object value;
    private final JpaOperator operator;

    private JpaFilter(String property, JpaOperator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public static JpaFilter filter(String property, JpaOperator operator) {
        if (operator.isBinary())
            throw new IllegalArgumentException("Binary Operator does not accept null");
        return new JpaFilter(property, operator, null);
    }

    public static JpaFilter filter(String property, JpaOperator operator, Object value) {
        if (operator.isUnary() || isNull(value))
            throw new IllegalArgumentException("Binary Operator does not accept null");
        return new JpaFilter(property, operator, value);
    }

    public String toWhere(String label) {
        return property + " " + operator + (operator.isUnary() ? "" : " :" + label);
    }

    public Optional<JpaParameter> toParameter(String label) {
        return operator.isUnary() ? Optional.empty() : Optional.of(parameter(label, value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaFilter that = (JpaFilter) o;
        return this.hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, value, operator);
    }

    @Override
    public String toString() {
        return property + operator + value;
    }
}
