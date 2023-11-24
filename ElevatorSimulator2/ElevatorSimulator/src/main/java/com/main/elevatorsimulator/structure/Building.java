package com.main.elevatorsimulator.structure;

import com.main.elevatorsimulator.structure.elevators.*;
import com.main.elevatorsimulator.structure.passengers.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * The Building class represents a building in
 * the elevator simulation, containing elevators and passengers.
 */
public class Building {
    private final int floors;
    private final int elevatorCount;
    private final ArrayList<Elevator> elevators = new ArrayList<>();
    private final ArrayList<Passenger> passengers = new ArrayList<>();
    private ArrayList<Passenger> waiting = new ArrayList<>();
    private final GridPane elevatorGrid;
    private final ElevatorController elevatorController;

    /**
     * Constructor for the Building class.
     *
     * @param floors        The total number of floors in the building.
     * @param elevatorCount The total number of elevators in the building.
     * @param elevatorGrid  The GridPane used to display elevators and passengers.
     */
    public Building(int floors, int elevatorCount, GridPane elevatorGrid) {
        this.floors = floors;
        this.elevatorCount = elevatorCount;
        this.elevatorGrid = elevatorGrid;
        this.elevatorController = new ElevatorController(this);
    }

    /**
     * Adds elevators to the building based on input parameters.
     *
     * @param inputFile An array containing input parameters for creating elevators.
     */
    public void addElevator(String[] inputFile) {
        // Extracting elevator parameters from the input file
        ElevatorType type = ElevatorType.valueOf(inputFile[0]);
        int maxCap = Integer.parseInt(inputFile[1]);
        int requestPer = Integer.parseInt(inputFile[2]);
        // Calculating the number of elevators based on the request percentage
        double result = ((double) requestPer / 100) * elevatorCount;
        // Round the result to the nearest integer
        int roundedResult = (int) Math.round(result);
        // Creating and adding elevators to the building based on the elevator type and count
        for (int i = 0; i < roundedResult; i++) {
            Elevator elevator;
            switch (type) {
                case StandardElevator -> elevator = new StandardElevator(type, maxCap);
                case ExpressElevator -> elevator = new ExpressElevator(type, maxCap);
                case GlassElevator -> elevator = new GlassElevator(type, maxCap);
                case FreightElevator -> elevator = new FreightElevator(type, maxCap);
                default -> elevator = null;
            }
            elevators.add(elevator);
        }
    }

    /**
     * Adds passengers to the building based on input parameters.
     *
     * @param inputFile An array containing input parameters for creating passengers.
     */
    public void addPassenger(String[] inputFile) {
        // Extracting passenger parameters from the input file
        int start = Integer.parseInt(inputFile[1]);
        int destination = Integer.parseInt(inputFile[2]);
        PassengerType type = PassengerType.valueOf(inputFile[3]);
        int requestPer = Integer.parseInt(inputFile[4]);

        // Creating and adding passengers to the building based on the passenger type
        Passenger passenger;
        switch (type) {
            case Standard -> passenger = new StandardPassenger(type, start, destination, requestPer);
            case VIP -> passenger = new VIPPassenger(type, start, destination, requestPer);
            case Glass -> passenger = new GlassPassenger(type, start, destination, requestPer);
            case Freight -> passenger = new FreightPassenger(type, start, destination, requestPer);
            default -> passenger = null;
        }
        passengers.add(passenger);
        waiting.add(passenger);
    }

    /**
     * Runs the elevator logic for each iteration of the simulation.
     */
    public void runElevators() {
        elevatorController.checkDestination();
        elevatorController.createRequest();
        updateGrid();
    }

    /**
     * Updates the GridPane to display the elevators, passengers, and floors.
     */
    public void updateGrid() {
        int column = 1; // Start from column 1 to add an empty column at the front
        for (Elevator elevator : elevators) {
            // Create a rectangle for the elevator
            Group elevatorRect = elevator.getElevator();

            // Check if the elevator is already in the GridPane
            // Remove the elevator from its current position
            elevatorGrid.getChildren().remove(elevatorRect);

            // Add the elevator to the GridPane at the specified column (elevator) and the last row
            elevatorGrid.add(elevatorRect, column, floors - elevator.getCurrentFloor());

            for (Passenger passenger : elevator.getPassengers()) {
                Circle circle = passenger.getPassenger();
                elevatorGrid.getChildren().remove(circle);
                elevatorGrid.add(circle, column, floors - elevator.getCurrentFloor());
            }
            column += 2; // Increase by 2 to leave an empty column between elevators
        }
    }

    /**
     * Adds floor labels to the GridPane.
     */
    public void addFloors() {
        for (int i = 0; i < floors; i++) {
            // Add a row constraint to the elevatorGrid for each floor
            RowConstraints rowConstraints = new RowConstraints(50);
            elevatorGrid.getRowConstraints().add(rowConstraints);
            // Create a label for the floor number
            Label floorLabel = new Label((floors - i) + ".Floor");
            // Set alignment to center
            floorLabel.setAlignment(Pos.CENTER);

            // Create a VBox to contain the label and align it at the top of the cell
            VBox vbox = new VBox(floorLabel);
            vbox.setAlignment(Pos.TOP_CENTER);

            // Add the VBox to the GridPane at the first column (0) and the current row
            elevatorGrid.add(vbox, 0, i);
        }
    }

    /**
     * Adds elevators to the GridPane.
     */
    public void addElevators() {
        int column = 1; // Start from column 1 to add an empty column at the front

        // Add an empty column at the beginning
        ColumnConstraints emptyColumn = new ColumnConstraints(50);
        elevatorGrid.getColumnConstraints().add(emptyColumn);

        for (Elevator elevator : elevators) {
            // Create a rectangle for the elevator
            Group elevatorRect = elevator.getElevator();

            // Add the elevator to the GridPane at the specified column (elevator) and the last row
            elevatorGrid.add(elevatorRect, column, floors);

            // Increment the column for the next elevator
            column += 2; // Increase by 2 to leave an empty column between elevators
        }
    }

    /**
     * Adds passengers to the GridPane.
     */
    public void addPassengers() {
        for (Passenger passenger : passengers) {
            // Create a circle for the passenger
            Circle passengerCircle = passenger.getPassenger();
            // Add the passenger circle to the GridPane at the specified column and row
            elevatorGrid.add(passengerCircle, 0, floors - passenger.getStart());
        }
    }

    public ArrayList<Elevator> getElevators() {
        return elevators;
    }

    public ArrayList<Passenger> getWaiting() {
        return passengers;
    }

    public void setWaiting(ArrayList<Passenger> waiting) {
        this.waiting = waiting;
    }

    public GridPane getElevatorGrid() {
        return elevatorGrid;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}
