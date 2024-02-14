# CarBookingApp
Service Classes:

UserService: Manages user-related operations such as adding users.
DriverService: Manages driver-related operations, including adding drivers, checking driver availability, and marking drivers as available or unavailable.
RideService: Handles ride-related functionalities, like finding available drivers within a certain distance and booking rides.

Model Classes:
User, Driver, and Location are model classes representing entities in the application.

Application Class (CarBookingApplication):
Uses instances of UserService, DriverService, and RideService.
Utilizes a simple console-based menu-driven interface to interact with users and drivers.
Accepts user inputs to perform actions such as adding users, adding drivers, finding rides, and choosing rides.
