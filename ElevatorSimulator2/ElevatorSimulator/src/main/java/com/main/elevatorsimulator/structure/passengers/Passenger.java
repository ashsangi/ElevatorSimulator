package com.main.elevatorsimulator.structure.passengers;

import javafx.scene.shape.Circle;

/**
 * The Passenger class represents a passenger in
 * the elevator simulation, with specific properties and information.
 */
public abstract class Passenger {
    private int waitingTime = 0;
    private int travelTime = 0;
    protected PassengerType type;
    protected int requestPercentage;
    protected int start;
    protected int destination;
    // Static variable to keep track of the next passenger ID
    private static int nextID = 1;
    // Unique identifier for each passenger
    private final int passengerID;
    protected Circle passenger;
    private boolean atDestination;
    private boolean insideElevator = false;

    /**
     * Constructor for the Passenger class.
     *
     * @param type              The type of the passenger.
     * @param start             The starting floor of the passenger.
     * @param destination       The destination floor of the passenger.
     * @param requestPercentage The percentage likelihood that the passenger will make a request for the elevator.
     */
    public Passenger(PassengerType type, int start, int destination, int requestPercentage) {
        this.type = type;
        this.start = start;
        this.destination = destination;
        this.requestPercentage = requestPercentage;
        this.passengerID = nextID++;
        createPassenger();
    }

    public PassengerType getType() {
        return type;
    }

    public abstract void createPassenger();

    public Circle getPassenger() {
        return passenger;
    }

    public int getRequestPercentage() {
        return requestPercentage;
    }

    public int getStart() {
        return start;
    }

    public int getDestination() {
        return destination;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public boolean isAtDestination() {
        return atDestination;
    }

    public void setAtDestination(boolean atDestination) {
        this.atDestination = atDestination;
    }

    public boolean isInsideElevator() {
        return insideElevator;
    }

    public void setInsideElevator(boolean insideElevator) {
        this.insideElevator = insideElevator;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime() {
        this.waitingTime++;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime() {
        this.travelTime++;
    }
}
