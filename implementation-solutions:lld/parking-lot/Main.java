import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingService parkingService = new ParkingService();

        // Level 1
        parkingService.plot.addSpot(new ParkingSpot(Sizes.SMALL, 1));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.SMALL, 1));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.MEDIUM, 1));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.MEDIUM, 1));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.LARGE, 1));

        // Level 2
        parkingService.plot.addSpot(new ParkingSpot(Sizes.SMALL, 2));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.SMALL, 2));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.MEDIUM, 2));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.MEDIUM, 2));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.LARGE, 2));

        // Level 3
        parkingService.plot.addSpot(new ParkingSpot(Sizes.SMALL, 3));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.MEDIUM, 3));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.MEDIUM, 3));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.LARGE, 3));
        parkingService.plot.addSpot(new ParkingSpot(Sizes.LARGE, 3));
        Vector<Vehicle> vehicles = new Vector<>();

        // Cars
        Car car1 = new Car("PB10AB1234");
        Car car2 = new Car("PB10CD5678");
        Car car3 = new Car("HR26EF9012");
        Car car4 = new Car("DL8CAF3456");

        // Bikes
        Bike bike1 = new Bike("PB10GH1122");
        Bike bike2 = new Bike("PB10JK3344");
        Bike bike3 = new Bike("HR26LM5566");
        Bike bike4 = new Bike("DL8CNQ7788");

        // Trucks
        Truck truck1 = new Truck("PB10TR1234");
        Truck truck2 = new Truck("HR55TR5678");
        Truck truck3 = new Truck("DL1LTR9012");
        Truck truck4 = new Truck("PB10TR3456");
        vehicles.addAll(List.of(
                car1, car2, car3, car4,
                bike1, bike2, bike3, bike4,
                truck1, truck2, truck3, truck4));

        while (true) {

            vehicles.forEach(v -> {
                System.out.println(v.type + " , " + v.licenseNumber + (v.parked ? " parked" : " not parked"));
            });

            System.out.println("Enter one of the two queries");
            System.out.println("To Park enter : p <license>");
            System.out.println("To Unpark enter : u <license>");

            String query = scanner.nextLine();

            if (query.startsWith("p")) {
                String licenseNum = query.substring(2);
                Optional<Vehicle> result = vehicles.stream()
                        .filter(vehicle -> vehicle.licenseNumber.equals(licenseNum))
                        .findFirst();

                if (!result.isPresent()) {
                    System.out.println("Please enter valid id");
                    return;
                }

                String message = parkingService.park(result.get());
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
                String licenseNumber = query.substring(2);
                Optional<Vehicle> result = vehicles.stream()
                        .filter(vehicle -> vehicle.licenseNumber.equals(licenseNumber))
                        .findFirst();

                if (!result.isPresent()) {
                    System.out.println("Please enter valid license number");
                    return;

                }
                Vehicle v = result.get();
                String message = parkingService.unpark(v);
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

            } else {
                System.out.println("Please enter valid query!");
            }

        }
    }
}
