package com.interview.Interview.LeetCodeDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignParkingLot {

    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance(5);

        // Create spots via Factory
        lot.addSpot("COMPACT");
        lot.addSpot("BIKE");
        lot.addSpot("LARGE");

        // Park vehicles via Factory
        lot.parkVehicle("CAR", "KA-01-1234");
        lot.parkVehicle("BIKE", "KA-02-5678");

        // Unpark vehicle
        lot.unparkVehicle("KA-01-1234");
    }
}

// ===== VEHICLE HIERARCHY =====
abstract class Vehicle {
    String licensePlate;
}

class Car extends Vehicle {}
class Bike extends Vehicle {}
class Truck extends Vehicle {}

class VehicleFactory {
    public static Vehicle createVehicle(String type, String licensePlate) {
        Vehicle v;
        switch (type.toUpperCase()) {
            case "CAR": v = new Car(); break;
            case "BIKE": v = new Bike(); break;
            case "TRUCK": v = new Truck(); break;
            default: throw new IllegalArgumentException("Unknown vehicle type");
        }
        v.licensePlate = licensePlate;
        return v;
    }
}

// ===== PARKING SPOT HIERARCHY =====
abstract class ParkingSpot {
    boolean isAvailable = true;
    Vehicle parkedVehicle;
}

class CompactSpot extends ParkingSpot {}
class LargeSpot extends ParkingSpot {}
class BikeSpot extends ParkingSpot {}

class ParkingSpotFactory {
    public static ParkingSpot createSpot(String type) {
        switch (type.toUpperCase()) {
            case "COMPACT": return new CompactSpot();
            case "LARGE": return new LargeSpot();
            case "BIKE": return new BikeSpot();
            default: throw new IllegalArgumentException("Unknown spot type");
        }
    }
}

// ===== PARKING LOT (Singleton) =====
class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingSpot> spots;
    private Map<Vehicle, ParkingSpot> parkedVehicles;
    private int capacity;

    private ParkingLot(int capacity) {
        this.capacity = capacity;
        this.spots = new ArrayList<>();
        this.parkedVehicles = new HashMap<>();
    }

    public static synchronized ParkingLot getInstance(int capacity) {
        if (instance == null) {
            instance = new ParkingLot(capacity);
        }
        return instance;
    }

    public void addSpot(String spotType) {
        if (spots.size() < capacity) {
            spots.add(ParkingSpotFactory.createSpot(spotType));
        } else {
            System.out.println("Parking lot is full, cannot add more spots!");
        }
    }

    public void parkVehicle(String vehicleType, String licensePlate) {
        Vehicle vehicle = VehicleFactory.createVehicle(vehicleType, licensePlate);

        for (ParkingSpot spot : spots) {
            if (spot.isAvailable) {
                spot.isAvailable = false;
                spot.parkedVehicle = vehicle;
                parkedVehicles.put(vehicle, spot);
                System.out.println(vehicleType + " parked with plate " + licensePlate);
                return;
            }
        }
        System.out.println("No available spots!");
    }

    public void unparkVehicle(String licensePlate) {
        Vehicle toRemove = null;
        for (Vehicle v : parkedVehicles.keySet()) {
            if (v.licensePlate.equals(licensePlate)) {
                toRemove = v;
                break;
            }
        }

        if (toRemove != null) {
            ParkingSpot spot = parkedVehicles.remove(toRemove);
            spot.isAvailable = true;
            spot.parkedVehicle = null;
            System.out.println("Vehicle with plate " + licensePlate + " left.");
        } else {
            System.out.println("No vehicle found with plate " + licensePlate);
        }
    }
}




