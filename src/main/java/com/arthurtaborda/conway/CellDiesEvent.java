package com.arthurtaborda.outfittery;

import java.util.Objects;
import java.util.StringJoiner;

public class CellDiesEvent {

    private final Point point;

    public CellDiesEvent(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellDiesEvent that = (CellDiesEvent) o;

        return Objects.equals(this.point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public String toString() {
        return "CellDiesEvent: " + point.toString();
    }
}
