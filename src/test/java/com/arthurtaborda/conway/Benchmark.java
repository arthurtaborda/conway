package com.arthurtaborda.conway;

import static java.util.Arrays.asList;

public class Benchmark {

    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();
        benchmark.efficient();
        benchmark.inefficient();
    }

    public void inefficient() {
        InefficientTable table = new InefficientTable(100, 100, asList(new Point(1, 1), new Point(1, 2), new Point(1, 3)), new
                SynchronousEventBus());

        System.out.println("Inefficient");
        for (int i = 0; i < 5; i++) {
            evaluate(table, 20);
        }
    }

    public void efficient() {
        EfficientTable table = new EfficientTable(10000000, 10000000, asList(new Point(1, 1), new Point(1, 2), new Point(1, 3)), new
                SynchronousEventBus());

        System.out.println("Efficient");
        for (int i = 0; i < 5; i++) {
            evaluate(table, 2000);
        }
    }

    private void evaluate(Table table, int times) {
        long before = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            table.tick();
        }
        long time = System.currentTimeMillis() - before;

        System.out.println(time);
    }
}
