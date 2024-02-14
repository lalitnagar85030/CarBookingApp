package org.example;

import org.example.model.Location;
import org.example.services.DriverService;
import org.example.services.RideService;
import org.example.services.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CarBookingApplication {
    private static final UserService userService = new UserService();
    private static final DriverService driverService = new DriverService();
    private static final RideService rideService = new RideService(driverService);

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            boolean run = true;

            while (run) {
                System.out.println("Choose an option:");
                System.out.println("1. Add User");
                System.out.println("2. Add Driver");
                System.out.println("3. Find Ride");
                System.out.println("4. Choose Ride");
                System.out.println("0. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter the userName: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter user Gender: ");
                        String gender = scanner.next();
                        System.out.println("Enter user age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        userService.addUser(name, gender, age);
                        break;
                    case 2:
                        System.out.println("Enter Driver Name:");
                        String dname = scanner.nextLine();
                        System.out.println("Enter Driver Gender: ");
                        String dgender = scanner.next();
                        System.out.println("Enter Driver age: ");
                        int dage = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Vehicle Model:");
                        String model = scanner.nextLine();
                        System.out.println("Enter Vehicle Number:");
                        String vehicleNumber = scanner.nextLine();
                        System.out.println("Enter Driver Location (x y):");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        scanner.nextLine();
                        driverService.addDriver(dname, dgender, dage, model, vehicleNumber, new Location(x, y));
                        break;
                    case 3:
                        System.out.println("Enter the userName: ");
                        String userName = scanner.nextLine();
                        System.out.println("Enter Your Location (x y):");
                        int x1 = scanner.nextInt();
                        int y1 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Destination Location (x y):");
                        int dx = scanner.nextInt();
                        int dy = scanner.nextInt();
                        scanner.nextLine();
                        rideService.findRide(userName, new Location(x1, y1), new Location(dx, dy));
                        break;
                    case 4:
                        System.out.println("Enter the userName: ");
                        String useName = scanner.nextLine();
                        System.out.println("Enter Driver Name:");
                        String driverName = scanner.nextLine();
                        rideService.chooseRide(useName, driverName);
                        break;
                    case 0:
                        System.out.println("Exit From the system");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            }
            scanner.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}