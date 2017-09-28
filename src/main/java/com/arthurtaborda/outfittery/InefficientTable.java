package com.arthurtaborda.outfittery;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class InefficientTable implements Table {

    private final EventBus eventBus;
    private final Boolean[][] cells;
    private final int length;
    private final int height;

    public InefficientTable(int length, int height, List<Point> initialAlive, EventBus eventBus) {
        this.length = length;
        this.height = height;
        this.eventBus = eventBus;
        cells = new Boolean[length][height];
        initCells(length, height, initialAlive);
    }

    private void initCells(int length, int height, List<Point> initialAlive) {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                Point point = new Point(x, y);
                cells[x][y] = initialAlive.contains(point);
            }
        }
    }

    @Override
    public void tick() {
        Queue<Point> dieEvents = new LinkedList<>();
        Queue<Point> liveEvents = new LinkedList<>();
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                Point point = new Point(x, y);
                long aliveNeighbours = aliveNeighbours(point);
                Boolean isAlive = cells[x][y];
                if (isAlive && aliveNeighbours < 2 || aliveNeighbours > 3) {
                    dieEvents.add(point);
                } else if (!isAlive && aliveNeighbours == 3) {
                    liveEvents.add(point);
                }
            }
        }

        for (Point point : dieEvents) {
            cells[point.x()][point.y()] = false;
            eventBus.send(new CellDiesEvent(point));
        }
        for (Point point : liveEvents) {
            cells[point.x()][point.y()] = true;
            eventBus.send(new CellLivesEvent(point));
        }
    }

    private long aliveNeighbours(Point point) {
        return getNeighbours(point).stream()
                                   .filter(Boolean::booleanValue)
                                   .count();
    }

    private List<Boolean> getNeighbours(Point point) {
        int startPosX = (point.x() - 1 < 0) ? point.x() : point.x() - 1;
        int startPosY = (point.y() - 1 < 0) ? point.y() : point.y() - 1;
        int endPosX = (point.x() + 1 > length) ? point.x() : point.x() + 1;
        int endPosY = (point.y() + 1 > height) ? point.y() : point.y() + 1;

        return asList(point.north(), point.northeast(),
                      point.east(), point.southeast(),
                      point.south(), point.southwest(),
                      point.west(), point.northwest()).stream()
                                                      .filter(p -> p.isValid(length, height))
                                                      .map(p -> cells[p.x()][p.y()])
                                                      .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Table\n");
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                string.append(cells[x][y] ? "1 " : "0 ");
            }
            string.append("\n");
        }
        return string.toString();
    }
}
