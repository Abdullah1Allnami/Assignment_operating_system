package org.example;

import java.util.*;
import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore semaphore;
    private static Map<String, List<Car>> gates = new HashMap<>();
    private static int servedCars = 0;

    public ParkingLot(int numberOfSpots) {
        this.semaphore = new Semaphore(numberOfSpots);
    }

    public void addCarToGate(String gate, Car car) {
        gates.computeIfAbsent(gate, k -> new ArrayList<>()).add(car);
        gates.get(gate).sort(Comparator.comparingInt(Car::getArrivalTime));
    }

    public void startParking() {
        for (Map.Entry<String, List<Car>> entry : gates.entrySet()) {
            Thread gateThread = new Thread(new Gate(entry.getValue()));
            gateThread.start();
        }
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public static void incrementServedCars() {
        servedCars++;
    }

    public static int getServedCars() {
        return servedCars;
    }
}
