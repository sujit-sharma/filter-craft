package io.github.himalays.plain.filter;

import java.io.Serial;
import java.io.Serializable;

public enum JpaOperator implements Serializable {
    EQUALS("=", false),
    NOT_EQUALS("!=", false),
    GREATER_THAN(">", false),
    GREATER_THAN_OR_EQUALS(">=", false),
    LESS_THAN("<", false),
    LESS_THAN_OR_EQUALS("<=", false),
    IN("IN", false),
    NOT_IN("NOT IN", false),
    IS_NULL("IS NULL", true),
    IS_NOT_NULL("IS NOT NULL", true);

    @Serial
    private static final long serialVersionUID = 1L;

    private final String value;
    private final boolean isUnary;

    JpaOperator(String value, boolean isUnary) {
        this.value = value;
        this.isUnary = isUnary;
    }

    public boolean isUnary() {
        return isUnary;
    }

    public boolean isBinary() {
        return !isUnary;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
