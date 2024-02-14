package org.example.services;


import org.example.exceptions.CabBookingException;
import org.example.model.Driver;
import org.example.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideService {

    @Autowired
    private final DriverService driverService;

    public RideService(DriverService driverService) {
        this.driverService = driverService;
    }

    public void findRide(String userName, Location userLocation, Location destination) {
        final double MAX_DISTANCE = 5.0;
        List<Driver> availableDrivers = driverService.getAllDrivers().stream()
                .filter(driver -> calculateDistance(driver.getVehicle().getLocation(), userLocation) <= MAX_DISTANCE)
                .collect(Collectors.toList());

        if (availableDrivers.isEmpty()) {
            System.out.println("No ride found");
        } else {
            Driver selectedDriver = availableDrivers.get(0);
            System.out.println(selectedDriver.getName() + " [Available]");
        }
    }

    private double calculateDistance(Location loc1, Location loc2) {
        return Math.sqrt(Math.pow(loc2.getX() - loc1.getX(), 2) + Math.pow(loc2.getY() - loc1.getY(), 2));
    }

    public void chooseRide(String userName, String driverName) throws CabBookingException {
        Driver driver = driverService.getDriver(driverName);
        if (driver == null) {
            throw new CabBookingException("Driver " + driverName + " not found.");
        }

        if (!driverService.isDriverAvailable(driverName)) {
            throw new CabBookingException("Driver " + driverName + " is not available.");
        }

        driverService.markDriverAsUnavailable(driverName);
        System.out.println("Ride booked with driver " + driverName + " by user " + userName);
    }
}
