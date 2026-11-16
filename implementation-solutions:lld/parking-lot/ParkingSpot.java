import java.util.UUID;

public class ParkingSpot {
    UUID id;
    VehicleTypes spotType;
    int level;
    boolean booked;
    Ticket ticket;

    ParkingSpot(VehicleTypes _type, int _level) {
        id = UUID.randomUUID();
        spotType = _type;
        level = _level;
        booked = false;
        ticket = null;
    }

    void book(Ticket _ticket) {
        booked = true;
        ticket = _ticket;
    }

    void free() {
        booked = false;
        ticket = null;
    }

    boolean isBooked() {
        return booked;
    }

}
