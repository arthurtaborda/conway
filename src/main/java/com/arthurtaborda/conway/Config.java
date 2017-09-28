package com.arthurtaborda.conway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class Config {

    private final long interval;
    private final int length;
    private final int height;
    private final List<List<Integer>> points;

    public Config(@JsonProperty(value = "interval", required = true) long interval,
                  @JsonProperty(value = "length", required = true) int length,
                  @JsonProperty(value = "height", required = true) int height,
                  @JsonProperty(value = "points", required = true) List<List<Integer>> points) {
        this.interval = interval;
        this.length = length;
        this.height = height;
        this.points = points;
    }

    public long getInterval() {
        return interval;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public List<Point> getPoints() {
        return points.stream()
                     .map(p -> new Point(p.get(0), p.get(1)))
                     .collect(Collectors.toList());
    }
}
