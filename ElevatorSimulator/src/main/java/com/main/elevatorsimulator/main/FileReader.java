package com.main.elevatorsimulator.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The FileReader class is responsible for reading
 * and processing input files for the elevator simulation.
 */
public class FileReader {
    private final File file;
    // Lists to store information from the input file
    private final ArrayList<String[]> passengers = new ArrayList<>();
    private final ArrayList<String[]> elevators = new ArrayList<>();
    private final ArrayList<String[]> passengerRequest = new ArrayList<>();
    private final ArrayList<String[]> elevatorRequest = new ArrayList<>();
    private int floors, elevatorCount, iterations;

    /**
     * Constructor for the FileReader class.
     *
     * @param file The input file to be read.
     */
    public FileReader(File file) {
        this.file = file;
    }

    /**
     * Reads the input file, processes the information
     * and populates relevant lists and variables.
     */
    public void readFile() {
        try {
            Scanner scanner = new Scanner(file);
            // Loop that reads each line of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip comment lines starting with # or empty lines
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }

                // Split the line into tokens based on spaces
                String[] tokens = line.split("\\s+");


                // Process each line based on its content
                if (tokens[0].contains("floors")) {
                    // Process the line containing information about the number of floors
                    String[] split = tokens[0].split("=");
                    floors = Integer.parseInt(split[1]);
                } else if (tokens[0].equals("add_passenger")) {
                    // Process lines representing passenger information
                    String[] inputLine = new String[tokens.length - 1];
                    System.arraycopy(tokens, 1, inputLine, 0, tokens.length - 1);
                    passengers.add(inputLine);
                } else if (tokens[0].equals("elevator_type")) {
                    // Process lines representing elevator type information
                    String[] inputLine = new String[tokens.length - 1];
                    System.arraycopy(tokens, 1, inputLine, 0, tokens.length - 1);
                    elevators.add(inputLine);
                } else if (tokens[0].equals("request_percentage")) {
                    // Process lines representing elevator request percentage information
                    String[] inputLine = new String[tokens.length - 1];
                    System.arraycopy(tokens, 1, inputLine, 0, tokens.length - 1);
                    elevatorRequest.add(inputLine);
                } else if (tokens[0].equals("passenger_request_percentage")) {
                    // Process lines representing passenger request percentage information
                    String[] inputLine = new String[tokens.length - 1];
                    System.arraycopy(tokens, 1, inputLine, 0, tokens.length - 1);
                    passengerRequest.add(inputLine);
                } else if (tokens[0].contains("number")) {
                    // Process lines representing the number of elevators
                    elevatorCount = Integer.parseInt(tokens[1]);
                } else if (tokens[0].contains("run")) {
                    // Process lines representing the number of simulation iterations
                    iterations = Integer.parseInt(tokens[1]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("STACKTRACE: " + Arrays.toString(e.getStackTrace()));
        }
    }

    public ArrayList<String[]> getPassengers() {
        return passengers;
    }

    public ArrayList<String[]> getElevators() {
        return elevators;
    }

    public ArrayList<String[]> getPassengerRequest() {
        return passengerRequest;
    }

    public ArrayList<String[]> getElevatorRequest() {
        return elevatorRequest;
    }

    public int getFloors() {
        return floors;
    }

    public int getElevatorCount() {
        return elevatorCount;
    }

    public int getIterations() {
        return iterations;
    }
}
