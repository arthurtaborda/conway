package com.arthurtaborda.conway.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView extends Rectangle {

    public CellView(boolean alive) {
        this.setWidth(10);
        this.setHeight(10);
        this.setStrokeWidth(1);
        if (alive) {
            toAlive();
        } else {
            toDead();
        }
    }

    void toAlive() {
        this.setFill(Color.BLACK);
        this.setStroke(Color.WHITE);
    }

    void toDead() {
        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
    }
}
