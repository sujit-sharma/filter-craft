package com.himalays.jpaspecfilter.contract;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public enum JpaOperator implements OperatorAction {

    EQUALS("=", false) {

        @Override
        public <V extends Comparable<? super V>, T> Predicate applyCriteria(CriteriaBuilder cb, Path<T> path, Class<V> type, V value) {
            return cb.equal(path, value);
        }
    },
    GREATER_THAN(">", false) {

        @Override
        public <V extends Comparable<? super V>, T> Predicate applyCriteria(CriteriaBuilder cb, Path<T> path, Class<V> type, V value) {
            return cb.greaterThan(path.as(type), value);
        }
    },
    GREATER_THAN_OR_EQUALS(">=", false) {

        @Override
        public <V extends Comparable<? super V>, T> Predicate applyCriteria(CriteriaBuilder cb, Path<T> path, Class<V> type, V value) {
            return cb.greaterThanOrEqualTo(path.as(type), value);
        }
    },
    LESS_THAN("<", false) {

        @Override
        public <V extends Comparable<? super V>, T> Predicate applyCriteria(CriteriaBuilder cb, Path<T> path, Class<V> type, V value) {
            return cb.lessThan(path.as(type), value);
        }
    },
    LESS_THAN_OR_EQUALS("<=", false) {

        @Override
        public <V extends Comparable<? super V>, T> Predicate applyCriteria(CriteriaBuilder cb, Path<T> path, Class<V> type, V value) {
            return cb.lessThanOrEqualTo(path.as(type), value);
        }
    };

//    IN("IN", false){
//
//        @Override
//        public <V extends Comparable<? super V>, T> Predicate applyCriteria(CriteriaBuilder cb, Path<T> path, Class<V> type, V value) {
//            return cb.in(path.as(type), value);
//        }
//    },
//    NOT_IN("NOT IN", false) {
//
//        @Override
//        public <T> Predicate applyCriteria(CriteriaBuilder criteriaBuilder, T convertHandler) {
//            return null;
//        }
//    },
//    IS_NULL("IS NULL", true){
//
//        @Override
//        public <T> Predicate applyCriteria(CriteriaBuilder criteriaBuilder, T convertHandler) {
//            return null;
//        }
//    },
//    IS_NOT_NULL("IS NOT NULL", true) {
//
//        @Override
//        public <T> Predicate applyCriteria(CriteriaBuilder criteriaBuilder, T convertHandler) {
//            return null;
//        }
//    };


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
