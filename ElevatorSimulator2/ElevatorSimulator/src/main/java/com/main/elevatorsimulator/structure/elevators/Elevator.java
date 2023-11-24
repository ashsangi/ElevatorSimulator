package com.main.elevatorsimulator.structure.elevators;

import com.main.elevatorsimulator.structure.passengers.Passenger;
import com.main.elevatorsimulator.structure.passengers.PassengerType;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Elevator class represents a generic elevator with basic properties and functionality.
 */
public abstract class Elevator {
    protected ElevatorType type;
    protected int maxCapacity;
    protected int currentFloor = 1;
    protected PassengerType favouriteType;
    // List of passengers currently inside the elevator
    protected ArrayList<Passenger> passengers = new ArrayList<>();
    private final ArrayList<Passenger> stillWaiting = new ArrayList<>();
    private boolean moveUp = true;
    protected Group elevator;
    private int destination = 0;
    protected Label passengerCountLabel;
    protected Group elevatorGroup;

    /**
     * Constructor for the Elevator class.
     *
     * @param type        The type of the elevator.
     * @param maxCapacity The maximum capacity of the elevator in terms of passengers.
     */
    public Elevator(ElevatorType type, int maxCapacity) {
        this.type = type;
        this.maxCapacity = maxCapacity;
        createElevator();
    }

    /**
     * Handles elevator requests and updates the list of passengers inside the elevator.
     *
     * @param waiting The list of waiting passengers.
     */
    public void handleRequests(ArrayList<Passenger> waiting) {
        int count = 0;
        while (passengers.size() < maxCapacity && count < waiting.size()) {
            Passenger passenger = waiting.get(count);
            if ((passenger.getType().equals(favouriteType) || favouriteType == PassengerType.Standard) && !passenger.isInsideElevator()) {
                if (currentFloor == passenger.getStart()) {
                    passengers.add(passenger);
                    passenger.setInsideElevator(true);
                    setDestination(passenger.getDestination());
                }
            }
            count++;
        }
        stillWaiting.clear();
        stillWaiting.addAll(passengers);
        updateLabel();
    }

    /**
     * Handles the destination of passengers inside the elevator and removes them when they reach their destination.
     */
    public void handlePassengerDestination() {
        Iterator<Passenger> iterator = passengers.iterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (getDestination() == passenger.getDestination() && currentFloor == getDestination()) {
                passenger.setAtDestination(true);
                passenger.setInsideElevator(false);
                iterator.remove();
            }
        }
        updateLabel();
    }

    /**
     * Updates the passenger count label.
     */
    protected void updateLabel() {
        passengerCountLabel.setText("Count:" + passengers.size());
    }

    /**
     * Creates a label for displaying the passenger count.
     */
    protected void createLabel() {
        // Create a label for passenger count
        passengerCountLabel = new Label("Count:" + passengers.size());
        passengerCountLabel.setLayoutX(5);
        passengerCountLabel.setLayoutY(-15);
    }

    public Group getElevator() {
        return elevator;
    }

    public abstract void createElevator();

    public void moveUp() {
        currentFloor++;
    }

    public void moveDown() {
        currentFloor--;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }


    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public ArrayList<Passenger> getStillWaiting() {
        return stillWaiting;
    }

    public abstract boolean isFavouriteType(PassengerType passengerType);

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }

    public ElevatorType getType() {
        return type;
    }
}
