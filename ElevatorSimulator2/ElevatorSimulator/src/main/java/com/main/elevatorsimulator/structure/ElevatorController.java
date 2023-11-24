package com.main.elevatorsimulator.structure;

import com.main.elevatorsimulator.structure.elevators.Elevator;
import com.main.elevatorsimulator.structure.passengers.Passenger;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * The ElevatorController class manages the movement and behavior of elevators in the building.
 */
public class ElevatorController {
    private final Building building;

    /**
     * Constructor for the ElevatorController class.
     *
     * @param building The building associated with the elevator controller.
     */
    public ElevatorController(Building building) {
        this.building = building;
    }

    /**
     * Creates elevator requests and updates the waiting passengers.
     */
    public void createRequest() {
        moveElevatorForPassenger();
        for (Elevator elevator : building.getElevators()) {
            ArrayList<Passenger> waitingStill = building.getWaiting();
            waitingStill.removeAll(elevator.getStillWaiting());
            building.setWaiting(waitingStill);
        }
    }

    /**
     * Moves elevators based on their destination floors.
     */
    private void moveElevators() {
        for (Elevator elevator : building.getElevators()) {
            if (elevator.getCurrentFloor() != elevator.getDestination() &&
                    elevator.getDestination() != 0) {
                move(elevator);
            } else {
                elevator.setDestination(0);
            }
        }
    }

    /**
     * Moves a specific elevator based on its destination floor.
     *
     * @param elevator The elevator to be moved.
     */
    private void move(Elevator elevator) {
        if (elevator.getDestination() >= elevator.getCurrentFloor()) {
            elevator.moveUp();
        } else {
            elevator.moveDown();
        }
        elevator.handleRequests(building.getWaiting());
    }

    /**
     * Checks and handles the destination of elevators and passengers.
     */
    public void checkDestination() {
        for (Elevator elevator : building.getElevators()) {
            elevator.handlePassengerDestination();
        }
        for (Passenger passenger : building.getPassengers()) {
            if (passenger.isAtDestination()) {
                Circle circle = passenger.getPassenger();
                building.getElevatorGrid().getChildren().remove(circle);
            }
        }
    }

    /**
     * Moves elevators to the starting floor of waiting passengers.
     */
    public void moveElevatorForPassenger() {
        for (Passenger waitingPassenger : building.getWaiting()) {
            int closestFloor = findClosestElevatorFloor(waitingPassenger);
            for (Elevator elevator : building.getElevators()) {
                if (elevator.getCurrentFloor() == closestFloor && elevator.isFavouriteType(waitingPassenger.getType())) {
                    elevator.setDestination(waitingPassenger.getStart());
                    break;
                }
            }
        }
        moveElevators();
    }

    /**
     * Finds the closest floor of an elevator that matches the type preference of a passenger.
     *
     * @param passenger The passenger for whom the closest elevator floor is found.
     * @return The closest floor of an elevator.
     */
    private int findClosestElevatorFloor(Passenger passenger) {
        int elevatorFloor = 0;
        int closestFloor = Integer.MAX_VALUE;
        for (Elevator elevator : building.getElevators()) {
            int distance = Math.abs(elevator.getCurrentFloor() - passenger.getStart());
            if (distance < closestFloor && elevator.isFavouriteType(passenger.getType())) {
                closestFloor = distance;
                elevatorFloor = elevator.getCurrentFloor();
            }
        }
        return elevatorFloor;
    }
}
