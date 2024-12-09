package io.github.himalays.filter;

import jakarta.persistence.LockModeType;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;

public class JpaQuery {

    private final String statement;
    private final String whereClause;
    private final List<JpaParameter> parameters;
    private final String orderClause;
    private final int position;
    private final int maxResult;
    private final LockModeType lockModeType;
    private final Map<String, Object> hints;

    private JpaQuery(String statement, String whereClause, List<JpaParameter> parameters, String orderClause, int position, int maxResult, LockModeType lockModeType, Map<String, Object> hints) {
        this.statement = statement;
        this.whereClause = whereClause;
        this.parameters = parameters;
        this.orderClause = orderClause;
        this.position = position;
        this.maxResult = maxResult;
        this.lockModeType = lockModeType;
        this.hints = hints;
    }

    public static JpaQuery query(String statement, String whereClause, List<JpaParameter> parameters, String orderClause, int position, int maxResult, LockModeType lockModeType) {
        return new JpaQuery(statement, whereClause, parameters, orderClause, position, maxResult, lockModeType, emptyMap());
    }

    public static JpaQuery query(String statement, String whereClause, List<JpaParameter> parameters, String orderClause, int position, int maxResult) {
        return query(statement, whereClause, parameters, orderClause, position, maxResult, null);
    }

    public static JpaQuery query(String statement, String whereClause, JpaParameter... parameters) {
        return query(statement, whereClause, asList(parameters), "", 0, 0);
    }

    public static JpaQuery query(String statement, String whereClause, List<JpaParameter> parameters) {
        return query(statement, whereClause, parameters, "", 0, 0);
    }

    public static JpaQuery query(String statement, String whereClause, List<JpaParameter> parameters, String orderClause, int position, int maxResult, LockModeType lockModeType, Map<String, Object> hints) {
        return new JpaQuery(statement, whereClause, parameters, orderClause, position, maxResult, lockModeType, hints);
    }

    public String getStatement() {
        return statement;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public List<JpaParameter> getParameters() {
        return parameters;
    }

    public String getOrderClause() {
        return orderClause;
    }

    public int getPosition() {
        return position;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public LockModeType getLockModeType() {
        return lockModeType;
    }

    public Map<String, Object> getHints() {
        return hints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaQuery that = (JpaQuery) o;
        return this.hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, whereClause, parameters, orderClause, position, maxResult, lockModeType, hints);
    }

    @Override
    public String toString() {
        return "JpaQuery{" +
                "statement='" + statement + '\'' +
                ", whereClause='" + whereClause + '\'' +
                ", parameters=" + parameters +
                ", orderClause='" + orderClause + '\'' +
                ", position=" + position +
                ", maxResult=" + maxResult +
                ", lockModeType=" + lockModeType +
                ", hints=" + hints +
                '}';
    }
}
