package org.example;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private final int id;
    private final int duration;
    private final int arrivalTime;
    private final String gate;
    private final ParkingLot parkingLot;

    public Car(int id, int duration, int arrivalTime, String gate, ParkingLot parkingLot) {
        this.id = id;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
        this.gate = gate;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            // Simulate arrival time by sleeping
            Thread.sleep(arrivalTime * 1000);
            System.out.println("Car " + id + " from " + gate + " arrived at time " + arrivalTime);

            // Acquire semaphore to check parking availability
            Sema_phore semaphore = parkingLot.getSemaphore();

            // Try to park if space is available, else wait
            if (semaphore.tryAcquire()) {
                System.out.println("Car " + id + " from " + gate + " parked. (Parking Status: " +
                        (4 - semaphore.availablePermits()) + " spots occupied)");
            } else {
                System.out.println("Car " + id + " from " + gate + " waiting for a spot.");
                long startWaitTime = System.currentTimeMillis();

                // Block until a spot is available
                semaphore.acquire();

                long waitTime = (System.currentTimeMillis() - startWaitTime) / 1000; // Wait time in seconds
                System.out.println("Car " + id + " from " + gate + " parked after waiting for " + waitTime + " units of time. (Parking Status: " +
                        (4 - semaphore.availablePermits() ) + " spots occupied)");
            }

            // Simulate parking duration
            Thread.sleep(duration * 1000);


            System.out.println("Car " + id + " from " + gate + " left after " + duration + " units of time. " +
                    "(Parking Status: " + (4 - semaphore.availablePermits()- 1) + " spots occupied)");

            // Release the parking spot
            semaphore.release();


            ParkingLot.incrementServedCars();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}
