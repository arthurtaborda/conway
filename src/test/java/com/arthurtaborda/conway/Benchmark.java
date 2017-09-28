package com.arthurtaborda.outfittery;

import static java.util.Arrays.asList;

public class Benchmark {

    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();
        benchmark.inefficient();
        benchmark.efficient();
    }

    public void inefficient() {
        InefficientTable table = new InefficientTable(100, 100, asList(new Point(1, 1), new Point(1, 2), new Point(1, 3)), new
                SynchronousEventBus());

        long before = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            table.tick();
        }
        long time = System.currentTimeMillis() - before;

        System.out.printf("Inefficient %d\n", time);
    }

    public void efficient() {
        EfficientTable table = new EfficientTable(10000000, 10000000, asList(new Point(1, 1), new Point(1, 2), new Point(1, 3)), new
                SynchronousEventBus());

        long before = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            table.tick();
        }
        long time = System.currentTimeMillis() - before;

        System.out.printf("Efficient %d", time);
    }
}
