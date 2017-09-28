package com.arthurtaborda.conway.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView extends Rectangle {

    public CellView(boolean alive) {
        this.setWidth(10);
        this.setHeight(10);
        this.setStrokeWidth(0.4);
        this.setStroke(Color.BLACK);
        if (alive) {
            toAlive();
        } else {
            toDead();
        }
    }

    synchronized void toAlive() {
        this.setFill(Color.BLACK);
    }

    synchronized void toDead() {
        this.setFill(Color.WHITE);
    }
}
