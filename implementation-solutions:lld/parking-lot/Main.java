import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot plot = new ParkingLot();

        // Level 1
        plot.addSpot(new ParkingSpot(VehicleTypes.Bike, 1));
        plot.addSpot(new ParkingSpot(VehicleTypes.Bike, 1));
        plot.addSpot(new ParkingSpot(VehicleTypes.Car, 1));
        plot.addSpot(new ParkingSpot(VehicleTypes.Car, 1));
        plot.addSpot(new ParkingSpot(VehicleTypes.Truck, 1));

        // Level 2
        plot.addSpot(new ParkingSpot(VehicleTypes.Bike, 2));
        plot.addSpot(new ParkingSpot(VehicleTypes.Bike, 2));
        plot.addSpot(new ParkingSpot(VehicleTypes.Car, 2));
        plot.addSpot(new ParkingSpot(VehicleTypes.Car, 2));
        plot.addSpot(new ParkingSpot(VehicleTypes.Truck, 2));

        // Level 3
        plot.addSpot(new ParkingSpot(VehicleTypes.Bike, 3));
        plot.addSpot(new ParkingSpot(VehicleTypes.Car, 3));
        plot.addSpot(new ParkingSpot(VehicleTypes.Car, 3));
        plot.addSpot(new ParkingSpot(VehicleTypes.Truck, 3));
        plot.addSpot(new ParkingSpot(VehicleTypes.Truck, 3));

        Vector<Vehicle> vehicles = new Vector<>();

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();

        // car1.parked = true;
        // car2.parked = true;

        // Bikes
        Bike bike1 = new Bike();
        Bike bike2 = new Bike();
        Bike bike3 = new Bike();
        Bike bike4 = new Bike();

        // bike1.parked = true;
        // bike2.parked = true;

        // Trucks
        Truck truck1 = new Truck();
        Truck truck2 = new Truck();
        Truck truck3 = new Truck();
        Truck truck4 = new Truck();

        vehicles.addAll(List.of(
                car1, car2, car3, car4,
                bike1, bike2, bike3, bike4,
                truck1, truck2, truck3, truck4));

        while (true) {

            vehicles.forEach(v -> {
                System.out.println(v.type + " , " + v.id + (v.parked ? " parked" : " not parked"));
            });

            System.out.println("Enter one of the two queries");
            System.out.println("To Park enter : p <vehicleId>");
            System.out.println("To Unpark enter : u <vehicleId>");

            String query = scanner.nextLine();

            if (query.startsWith("p")) {
                String vehicleId = query.substring(2);
                Optional<Vehicle> result = vehicles.stream()
                        .filter(vehicle -> vehicle.id.equals(UUID.fromString(vehicleId)))
                        .findFirst();
                if (!result.isPresent()) {
                    System.out.println("Please enter valid id");
                    return;
                }

                String message = plot.park(result.get());
                String output = "|  " + message + "  |";

                // String output = "| Parked successfully! " + ticket.vehicleType +
                // " " + ticket.id + ticket.spotLevel + " |";

                String border = "";
                int len = output.length();
                while (len != 0) {
                    border += "-";
                    len--;
                }

                System.out.println(border);
                System.out.println(output);
                System.out.println(border);

            } else if (query.startsWith("u")) {
                System.out.println("Can't unpark yet");
            } else {
                System.out.println("Please enter valid query!");
            }

        }
    }
}
