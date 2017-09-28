package com.arthurtaborda.outfittery;

import java.util.List;

public class EfficientTableTest extends GameSpecification {

    protected Table getTable(List<Point> initialAlive, EventBusSpy eventBus) {
        return new EfficientTable(10, 10, initialAlive, eventBus);
    }
}
