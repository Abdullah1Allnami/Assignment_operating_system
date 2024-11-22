package org.example;

public class Sema_phore {

    private final int numberOfSpots;
    private int numberOfUsed = 0;

    public Sema_phore(int numberOfSpots) {
        this.numberOfSpots = numberOfSpots;
    }

    public synchronized boolean tryAcquire() {
        if (numberOfUsed < numberOfSpots) {
            numberOfUsed++;
            return true;
        }
        return false;
    }

    public synchronized int availablePermits() {
        return numberOfSpots - numberOfUsed;
    }

    public synchronized void acquire() throws InterruptedException {
        while (numberOfUsed >= numberOfSpots) {
            wait(); // Wait for a spot to become available
        }
        numberOfUsed++;
    }

    public synchronized void release() {
        if (numberOfUsed > 0) {
            numberOfUsed--;
            notifyAll(); // Notify all waiting threads that a spot is available
        }
    }
}
