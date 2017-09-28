package com.arthurtaborda.conway;

import java.util.List;

public class InefficientTableTest extends GameSpecification {

    protected Table getTable(List<Point> initialAlive, EventBusSpy eventBus) {
        return new InefficientTable(10, 10, initialAlive, eventBus);
    }
}
