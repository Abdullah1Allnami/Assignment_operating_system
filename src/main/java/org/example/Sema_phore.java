package org.example;

public class Sema_phore {

    private final int numberOfSpots;
    private int numberOfUsed = 0;

    public Sema_phore(int numberOfSpots) {
        this.numberOfSpots = numberOfSpots;
    }

    // synchronized for one thread only execute the block of code at a time
    public synchronized boolean tryAcquire() {
        if (numberOfUsed < numberOfSpots) {
            numberOfUsed++;
            return true;
        } else {
            return false;
        }
    }

    public synchronized int availablePermits() {
        return numberOfSpots - numberOfUsed;
    }

    public synchronized void acquire() throws InterruptedException {
        while (numberOfUsed >= numberOfSpots) {
            wait(); // wait until another thread calls
        }
        numberOfUsed++;
    }

    public synchronized void release() {
        if (numberOfUsed > 0) {
            numberOfUsed--;
            notify(); // Notify a waiting thread that a spot is available
        }
    }
}
