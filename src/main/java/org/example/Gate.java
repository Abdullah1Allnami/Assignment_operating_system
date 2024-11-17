package org.example;

import java.util.List;

public class Gate implements Runnable {
    private final List<Car> cars;

    public Gate(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public void run() {
        for (Car car : cars) {
            new Thread(car).start();
        }
    }
}
