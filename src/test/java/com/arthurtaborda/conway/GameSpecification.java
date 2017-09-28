package com.arthurtaborda.conway;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public abstract class GameSpecification {

    private EventBusSpy eventBus;

    @Before
    public void setUp() throws Exception {
        eventBus = new EventBusSpy();
    }

    protected abstract Table getTable(List<Point> initialAlive, EventBusSpy eventBus);

    @Test
    public void whenLiveCellHasNoNeighbours_diesInNextTick() {
        Point point = new Point(2, 2);
        Table table = getTable(asList(point), eventBus);

        table.tick();

        assertThat(eventBus.getEventsSent()).hasSize(1).contains(new CellDiesEvent(point));
    }

    @Test
    public void whenLiveCellHasOneNeighbour_bothDieInNextTick() {
        Point point1 = new Point(2, 2);
        Point point2 = new Point(2, 3);
        Table table = getTable(asList(point1, point2), eventBus);

        table.tick();

        assertThat(eventBus.getEventsSent()).hasSize(2)
                                            .contains(new CellDiesEvent(point1))
                                            .contains(new CellDiesEvent(point2));
    }

    @Test
    public void whenLiveCellHasTwoNeighbours_stayAlive() {
        Point point1 = new Point(2, 2);
        Point point2 = new Point(2, 3);
        Point point3 = new Point(2, 4);
        Table table = getTable(asList(point1, point2, point3), eventBus);

        table.tick();

        assertThat(eventBus.getEventsSent()).doesNotContain(new CellDiesEvent(point2));
    }

    @Test
    public void whenLiveCellHasThreeNeighbours_stayAlive() {
        Point point1 = new Point(2, 2);
        Point point2 = new Point(2, 3);
        Point point3 = new Point(2, 4);
        Point point4 = new Point(1, 3);
        Table table = getTable(asList(point1, point2, point3, point4), eventBus);

        table.tick();

        assertThat(eventBus.getEventsSent()).doesNotContain(new CellDiesEvent(point2))
                                            .doesNotContain(new CellDiesEvent(point4));
    }

    @Test
    public void whenCellChangeState_useChangedStateInNextTick() {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 1);
        Point point3 = new Point(1, 0);
        Point point4 = new Point(3, 3);
        Point point5 = new Point(3, 2);
        Point point6 = new Point(2, 3);
        Table table = getTable(asList(point1, point2, point3, point4, point5, point6), eventBus);

        System.out.println(table);
        table.tick();
        System.out.println(table);
        assertThat(eventBus.getEventsSent()).contains(new CellLivesEvent(new Point(1, 1)))
                                            .contains(new CellLivesEvent(new Point(2, 2)));

        eventBus.clear();

        table.tick();
        System.out.println(table);

        assertThat(eventBus.getEventsSent()).contains(new CellDiesEvent(new Point(1, 1)))
                                            .contains(new CellDiesEvent(new Point(2, 2)));
    }

    @Test
    public void whenDeadCellHasThreeNeighbours_live() {
        Point point1 = new Point(2, 2);
        Point point2 = new Point(2, 3);
        Point point3 = new Point(2, 4);
        Point point4 = new Point(1, 3);
        Table table = getTable(asList(point1, point3, point4), eventBus);

        table.tick();

        assertThat(eventBus.getEventsSent()).contains(new CellLivesEvent(point2));
    }
}
