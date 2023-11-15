package com.main.elevatorsimulator.structure.elevators;

import com.main.elevatorsimulator.structure.passengers.PassengerType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class StandardElevator extends Elevator{
    /**
     * Constructor for the Elevator class.
     *
     * @param type        The type of the elevator.
     * @param maxCapacity The maximum capacity of the elevator in terms of passengers.
     */
    public StandardElevator(ElevatorType type, int maxCapacity) {
        super(type, maxCapacity);
        this.favouriteType = PassengerType.Standard;
    }
    @Override
    public void createElevator() {
        this.elevator = new Rectangle(50, 50);
        elevator.setFill(Color.LIGHTGRAY);
        elevator.setStroke(Color.BLACK);
    }
}
