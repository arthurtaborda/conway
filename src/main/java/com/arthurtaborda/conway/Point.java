package com.arthurtaborda.conway;

import java.util.Objects;
import java.util.StringJoiner;

public class Point {

    private final int x;
    private final int y;
    private final int hash;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.hash = Objects.hash(x, y);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public boolean isValid(int length, int height) {
        return this.x >= 0 && this.x < length && this.y >= 0 && this.y < height;
    }

    public Point north() {
        return new Point(x, y - 1);
    }

    public Point northeast() {
        return new Point(x + 1, y - 1);
    }

    public Point northwest() {
        return new Point(x - 1, y - 1);
    }

    public Point south() {
        return new Point(x, y + 1);
    }

    public Point southeast() {
        return new Point(x + 1, y + 1);
    }

    public Point southwest() {
        return new Point(x - 1, y + 1);
    }

    public Point east() {
        return new Point(x + 1, y);
    }

    public Point west() {
        return new Point(x - 1, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point that = (Point) o;

        return Objects.equals(this.x, that.x) &&
                Objects.equals(this.y, that.y);
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add(String.valueOf(x))
                .add(String.valueOf(y))
                .toString();
    }
}
