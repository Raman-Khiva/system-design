import java.util.UUID;

class Vehicle {
    UUID id;
    VehicleTypes type;
    Sizes size;
    String licenseNumber;
    boolean parked;
    Ticket ticket;

    Vehicle(VehicleTypes _type, Sizes _size, String _licensenumber) {
        id = UUID.randomUUID();
        licenseNumber = _licensenumber;
        type = _type;
        parked = false;
        ticket = null;
        size = _size;
    }

    void park(Ticket _ticket) {
        this.parked = true;
        this.ticket = _ticket;
    }

    void unpark() {
        this.ticket = null;
        this.parked = false;
    }
}

class Car extends Vehicle {

    Car(String _licensenumber) {
        super(VehicleTypes.Car, Sizes.MEDIUM, _licensenumber);
    }
}

class Bike extends Vehicle {
    Bike(String _licensenumber) {
        super(VehicleTypes.Bike, Sizes.SMALL, _licensenumber);
    }

}

class Truck extends Vehicle {

    Truck(String _licensenumber) {
        super(VehicleTypes.Truck, Sizes.LARGE, _licensenumber);
    }

}
