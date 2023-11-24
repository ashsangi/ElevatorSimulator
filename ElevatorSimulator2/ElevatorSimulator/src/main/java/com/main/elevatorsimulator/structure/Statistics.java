package com.main.elevatorsimulator.structure;


import com.main.elevatorsimulator.structure.passengers.Passenger;

import java.util.ArrayList;

public class Statistics {
    private final ArrayList<Passenger> passengers;

    public Statistics(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void addPassengerStatistic() {
        for (Passenger passenger : passengers) {
            if (passenger.isInsideElevator()) {
                passenger.setTravelTime();
            } else if (!passenger.isAtDestination()) {
                passenger.setWaitingTime();
            }
        }
    }

    public void printStats() {
        for (Passenger passenger : passengers) {
            System.out.print("Passenger " + passenger.getPassengerID() + " waited for " + passenger.getWaitingTime() + "\t");
            System.out.println("traveled for " + passenger.getTravelTime());
        }
    }
}
