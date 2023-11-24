package com.main.elevatorsimulator.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    public GridPane elevatorGrid;

    private final Simulation simulation;

    public MainController() {
        simulation = new Simulation(this);
    }

    @FXML
    void onStartBtnPress(ActionEvent event) {
        simulation.createBuilding();
    }

    public GridPane getElevatorGrid() {
        return elevatorGrid;
    }
}
