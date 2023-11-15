package com.main.elevatorsimulator.structure.elevators;

import com.main.elevatorsimulator.structure.passengers.Passenger;
import com.main.elevatorsimulator.structure.passengers.PassengerType;
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
    private boolean moveUp = true;
    protected Rectangle elevator;

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

    public ElevatorType getType() {
        return type;
    }

    public ArrayList<Passenger> handleRequests(ArrayList<Passenger> waiting) {
        int count = 0;
        while (passengers.size() < maxCapacity && count < waiting.size()) {
            Passenger passenger = waiting.get(count);
            if (passenger.getType().equals(favouriteType) || favouriteType == PassengerType.Standard) {
                if (currentFloor == passenger.getStart()) {
                    passengers.add(passenger);
                    passenger.setAtDestination(true);
                }
            }
            count++;
        }
        waiting.removeAll(passengers);
        return waiting;
    }

    public abstract void createElevator();

    public Rectangle getElevator() {
        return elevator;
    }

    public void handleDestination() {
        Iterator<Passenger> iterator = passengers.iterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (currentFloor == passenger.getDestination()) {
                passenger.setAtDestination(true);
                iterator.remove();
            }
        }
    }

    public void moveUp() {
        currentFloor++;
    }

    public void moveDown() {
        currentFloor--;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}
