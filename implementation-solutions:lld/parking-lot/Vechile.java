import java.util.UUID;

class Vehicle {
    UUID id;
    VehicleTypes type;
    String licenseNumber;
    boolean parked;
    Ticket ticket;

    Vehicle(String _licensenumber) {
        id = UUID.randomUUID();
        licenseNumber = _licensenumber;
        type = null;
        parked = false;
        ticket = null;
    }

    void park(Ticket _ticket) {
        this.parked = true;
        this.ticket = _ticket;
    }
}

class Car extends Vehicle {

    Car() {
        type = VehicleTypes.Car;
    }
}

class Bike extends Vehicle {

    Bike() {
        type = VehicleTypes.Bike;
    }
}

class Truck extends Vehicle {
    Truck() {
        type = VehicleTypes.Truck;
    }
}
