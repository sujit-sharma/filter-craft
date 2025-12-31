package io.github.himalays.plain.filter;

import java.util.Objects;

public class JpaParameter {

    private final String name;
    private final Object value;

    private JpaParameter(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public static JpaParameter parameter(String name, Object value) {
        return new JpaParameter(name, value);
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaParameter that = (JpaParameter) o;
        return this.hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "JpaParameter{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
