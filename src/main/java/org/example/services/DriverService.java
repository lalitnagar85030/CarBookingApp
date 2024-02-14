package org.example.services;


import org.example.exceptions.CabBookingException;
import org.example.model.Driver;
import org.example.model.Location;
import org.example.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverService {
    private final Map<String, Driver> drivers = new HashMap<>();
    private final Map<String, Boolean> driverAvailability = new HashMap<>();

    public void addDriver(String name, String gender, int age, String model, String vehicleNumber, Location location) throws CabBookingException {
        if (drivers.containsKey(name)) {
            throw new CabBookingException("Driver with name " + name + " already exists.");
        }
        Vehicle vehicle = new Vehicle(model, vehicleNumber, location);
        Driver driver = new Driver(name, age, gender, vehicle);
        drivers.put(name, driver);
        markDriverAsAvailable(name);
    }

    public List<Driver> getAllDrivers() {
        return new ArrayList<>(drivers.values());
    }

    public boolean isDriverAvailable(String driverName) {
        return driverAvailability.getOrDefault(driverName, false);
    }

    public void markDriverAsUnavailable(String driverName) {
        driverAvailability.put(driverName, false);
    }

    public void markDriverAsAvailable(String driverName) {
        driverAvailability.put(driverName, true);
    }

    public Driver getDriver(String driverName) {
        return drivers.get(driverName);
    }
}
