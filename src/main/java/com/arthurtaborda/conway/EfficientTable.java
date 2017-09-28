package com.arthurtaborda.conway;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class EfficientTable implements Table {

    private final EventBus eventBus;
    private final int length;
    private final int height;
    private final Set<Point> alivePoints;

    public EfficientTable(int length, int height, List<Point> initialAlive, EventBus eventBus) {
        this.length = length;
        this.height = height;
        this.eventBus = eventBus;
        int maxCapacity = length * height;
        this.alivePoints = new LinkedHashSet<>(maxCapacity);
        this.alivePoints.addAll(initialAlive);

    }

    @Override
    public synchronized void tick() {
        Queue<Point> dieEvents = new LinkedList<>();
        Queue<Point> liveEvents = new LinkedList<>();
        Map<Point, Integer> pointsToAnalyse = getPointsToAnalyse();
        for (Point point : pointsToAnalyse.keySet()) {
            Boolean isAlive = isAlive(point);
            long aliveNeighbours = pointsToAnalyse.get(point);
            if (isAlive && aliveNeighbours < 2 || aliveNeighbours > 3) {
                dieEvents.add(point);
            } else if (!isAlive && aliveNeighbours == 3) {
                liveEvents.add(point);
            }
        }

        for (Point point : dieEvents) {
            alivePoints.remove(point);
            eventBus.send(new CellDiesEvent(point));
        }
        for (Point point : liveEvents) {
            alivePoints.add(point);
            eventBus.send(new CellLivesEvent(point));
        }
    }

    /**
     * @return points with amount of live neighbours
     */
    private Map<Point, Integer> getPointsToAnalyse() {
        Map<Point, Integer> pointsToAnalyse = new HashMap<>();
        for (Point point : alivePoints) {
            List<Point> neighbours = getNeighbours(point);
            pointsToAnalyse.put(point, aliveNeighbours(neighbours));
            addNeighbours(pointsToAnalyse, neighbours);
        }
        return pointsToAnalyse;
    }

    private void addNeighbours(Map<Point, Integer> pointsToAnalyse, List<Point> neighbours) {
        neighbours.forEach(neighbour -> {
            List<Point> newNeighbours = getNeighbours(neighbour);
            if (!pointsToAnalyse.containsKey(neighbour)) {
                pointsToAnalyse.put(neighbour, aliveNeighbours(newNeighbours));
                if (isAlive(neighbour)) {
                    addNeighbours(pointsToAnalyse, newNeighbours);
                }
            }
        });
    }

    private int aliveNeighbours(List<Point> neighbours) {
        return (int) neighbours.stream()
                               .filter(p -> isAlive(p))
                               .count();
    }

    private List<Point> getNeighbours(Point point) {
        int startPosX = (point.x() - 1 < 0) ? point.x() : point.x() - 1;
        int startPosY = (point.y() - 1 < 0) ? point.y() : point.y() - 1;
        int endPosX = (point.x() + 1 > length) ? point.x() : point.x() + 1;
        int endPosY = (point.y() + 1 > height) ? point.y() : point.y() + 1;

        List<Point> neighbours = new LinkedList<>();
        for (int x = startPosX; x <= endPosX; x++) {
            for (int y = startPosY; y <= endPosY; y++) {
                Point neighbour = new Point(x, y);
                if(!point.equals(neighbour)) {
                    neighbours.add(neighbour);
                }
            }
        }

        return neighbours;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Table\n");
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                string.append(isAlive(new Point(x, y)) ? "X " : ". ");
            }
            string.append("\n");
        }
        return string.toString();
    }

    private boolean isAlive(Point point) {
        return alivePoints.contains(point);
    }
}
