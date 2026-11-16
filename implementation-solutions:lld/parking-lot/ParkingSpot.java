import java.util.UUID;

public class ParkingSpot {
    UUID id;
    VehicleTypes spotType;
    Sizes capicity;
    int level;
    boolean booked;
    Ticket ticket;

    ParkingSpot(Sizes _capacity, int _level) {
        id = UUID.randomUUID();
        level = _level;
        booked = false;
        ticket = null;
        capicity = _capacity;
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
