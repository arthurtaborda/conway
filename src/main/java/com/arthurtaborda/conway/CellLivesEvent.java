package com.arthurtaborda.outfittery;

import java.util.Objects;

public class CellLivesEvent {

    private final Point point;

    public CellLivesEvent(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellLivesEvent that = (CellLivesEvent) o;

        return Objects.equals(this.point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public String toString() {
        return "CellLivesEvent: " + point.toString();
    }
}
