package com.arthurtaborda.conway;

import com.arthurtaborda.conway.gui.BoardView;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConwaysGame extends Application {

    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    private Table table;
    private EventBus eventBus;

    public static void main(String[] args) {
        Application.launch(ConwaysGame.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.eventBus = new SynchronousEventBus();
        Parameters parameters = getParameters();

        ObjectMapper objectMapper = new ObjectMapper();
        Config config = objectMapper.readValue(getClass().getClassLoader().getResource(parameters.getRaw().get(0)), Config.class);
        int length = config.getLength();
        int height = config.getHeight();
        List<Point> initialAlive = config.getPoints();

        this.table = new EfficientTable(length, height, initialAlive, eventBus);
        BoardView boardView = new BoardView(length, height, initialAlive);
        eventBus.subscribe(CellDiesEvent.class, boardView.new CellDiesEventHandler());
        eventBus.subscribe(CellLivesEvent.class, boardView.new CellLivesEventHandler());

        EXECUTOR_SERVICE.scheduleAtFixedRate(() -> table.tick(), 0, config.getInterval(), TimeUnit.MILLISECONDS);

        stage.setScene(new Scene(boardView));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}
