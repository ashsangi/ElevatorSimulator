package com.main.elevatorsimulator.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    public GridPane elevatorGrid;

    @FXML
    private Pane floorPane;

    @FXML
    private HBox waitingArea;

    private final Simulation simulation;

    public MainController() {
        simulation = new Simulation(this);
    }

    @FXML
    void onStartBtnPress(ActionEvent event) {
        simulation.createBuilding();
    }

    public void setFloorsAndElevators(int elevatorCount) {
        // Dynamically generate elevators based on the count
        for (int i = 0; i < elevatorCount; i++) {
            // Create waiting space next to each elevator
            Pane space = new Pane();
            space.setMinWidth(50); // Adjust the width as needed
            waitingArea.getChildren().add(space);
        }
    }

    public GridPane getElevatorGrid() {
        return elevatorGrid;
    }

    public Pane getFloorPane() {
        return floorPane;
    }

    public HBox getWaitingArea() {
        return waitingArea;
    }
}
