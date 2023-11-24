package com.main.elevatorsimulator.main;


import com.main.elevatorsimulator.structure.Building;
import com.main.elevatorsimulator.structure.Statistics;
import javafx.application.Platform;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Simulation class orchestrates the elevator simulation by reading input parameters,
 * creating a building, and running the simulation for a specified number of iterations.
 */
public class Simulation {
    private FileReader fileReader;
    private Building building;
    private int iterations;
    private final MainController mainController;
    private Statistics statistics;

    /**
     * Constructor for the Simulation class. Initiates the simulation by reading input parameters
     * and creating the building.
     */
    public Simulation(MainController mainController) {
        this.mainController = mainController;
        readFile();
    }

    /**
     * Reads input parameters from the specified
     * file and initializes the FileReader and Building.
     */
    private void readFile() {
        String path = "src/main/resources/inputFiles/InputParameter.txt";
        File file = new File(path);
        fileReader = new FileReader(file);
        fileReader.readFile();
    }

    /**
     * Creates a building based on the input
     * parameters obtained from the FileReader.
     */
    public void createBuilding() {
        building = new Building(fileReader.getFloors(), fileReader.getElevatorCount(), mainController.getElevatorGrid());
        for (String[] input : fileReader.getElevators()) {
            building.addElevator(input);
        }
        for (String[] input : fileReader.getPassengers()) {
            building.addPassenger(input);
        }
        this.iterations = fileReader.getIterations();
        Collections.reverse(building.getElevators());
        building.addFloors();
        building.addElevators();
        building.addPassengers();
        statistics = new Statistics(new ArrayList<>(building.getPassengers()));
        runSimulation();
    }

    /**
     * Runs the elevator simulation for the
     * specified number of iterations in a separate thread.
     * Each iteration involves generating passenger requests,
     * simulating elevator movement, and updating metrics.
     */
    public void runSimulation() {
        long waitTimeMillis = 500; // 0.5 second
        Thread t = new Thread(() -> {
            while (iterations > 0) {
                synchronized (building.getElevators()) {
                    logic();
                    statistics.addPassengerStatistic();
                    try {
                        building.getElevators().wait(waitTimeMillis);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    iterations--;
                }
            }
            statistics.printStats();
        });
        t.start();
    }

    /**
     * Performs the logic for each iteration of the simulation.
     * Steps include generating passenger requests, simulating
     * elevator movement, and updating metrics.
     */
    private void logic() {
        Platform.runLater(() -> {
            building.runElevators();
        });

    }
}
