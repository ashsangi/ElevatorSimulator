package com.main.elevatorsimulator.structure.elevators;

import com.main.elevatorsimulator.structure.passengers.PassengerType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FreightElevator extends Elevator{
    /**
     * Constructor for the Elevator class.
     *
     * @param type        The type of the elevator.
     * @param maxCapacity The maximum capacity of the elevator in terms of passengers.
     */
    public FreightElevator(ElevatorType type, int maxCapacity) {
        super(type, maxCapacity);
        this.favouriteType = PassengerType.Freight;
    }
    @Override
    public void createElevator() {
        this.elevator = new Rectangle(50, 50);
        elevator.setFill(Color.LIGHTGRAY);
        elevator.setStroke(Color.GREEN);
    }
}
