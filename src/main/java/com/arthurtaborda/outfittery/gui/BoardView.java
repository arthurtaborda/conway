package com.arthurtaborda.outfittery.gui;

import com.arthurtaborda.outfittery.CellDiesEvent;
import com.arthurtaborda.outfittery.CellLivesEvent;
import com.arthurtaborda.outfittery.EventHandler;
import com.arthurtaborda.outfittery.Point;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class BoardView extends VBox {

    private final CellView[][] cells;

    public BoardView(int length, int height, List<Point> initialAlive) {
        cells = new CellView[length][height];
        initCells(length, height, initialAlive);
    }

    private void initCells(int length, int height, List<Point> initialAlive) {
        for (int i = 0; i < length; i++) {
            HBox line = new HBox();
            for (int j = 0; j < height; j++) {
                Point point = new Point(i, j);
                cells[i][j] = new CellView(initialAlive.contains(point));
                line.getChildren().add(cells[i][j]);
            }
            getChildren().add(line);
        }
    }

    public class CellDiesEventHandler implements EventHandler<CellDiesEvent> {

        @Override
        public void handle(CellDiesEvent event) {
            cells[event.getPoint().x()][event.getPoint().y()].toDead();
        }
    }

    public class CellLivesEventHandler implements EventHandler<CellLivesEvent> {

        @Override
        public void handle(CellLivesEvent event) {
            cells[event.getPoint().x()][event.getPoint().y()].toAlive();
        }
    }
}
