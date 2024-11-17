package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParkingSystem {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/bdallhsydbdallh/Library/" +
                "Mobile Documents/com~apple~CloudDocs/4-OS/Labs/op_system/src/main/java/org/example/input.txt"))) {
            ParkingLot parkingLot = new ParkingLot(4); // 4 spots
            String line;

            while ((line = br.readLine()) != null) {
                String[] in_put = line.split(", ");
                String gate = in_put[0];
                int id = Integer.parseInt(in_put[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(in_put[2].split(" ")[1]);
                int duration = Integer.parseInt(in_put[3].split(" ")[1]);

                Car car = new Car(id, duration, arrivalTime, gate, parkingLot);
                parkingLot.addCarToGate(gate, car);
            }
            parkingLot.startParking();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
