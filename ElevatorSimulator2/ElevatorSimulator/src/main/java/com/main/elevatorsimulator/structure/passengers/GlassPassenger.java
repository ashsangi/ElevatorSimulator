package com.main.elevatorsimulator.structure.passengers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GlassPassenger extends Passenger {

    /**
     * Constructor for the Passenger class.
     *
     * @param type              The type of the passenger.
     * @param start             The starting floor of the passenger.
     * @param destination       The destination floor of the passenger.
     * @param requestPercentage The percentage likelihood that the passenger will make a request for the elevator.
     */
    public GlassPassenger(PassengerType type, int start, int destination, int requestPercentage) {
        super(type, start, destination, requestPercentage);
    }

    @Override
    public void createPassenger() {
        this.passenger = new Circle(15, Color.LIGHTBLUE);
    }
}
