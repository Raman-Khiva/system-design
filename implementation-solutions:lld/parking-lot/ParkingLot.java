
import java.util.Optional;
import java.util.Vector;

public class ParkingLot {
    Vector<ParkingSpot> spots;

    // boolean park(Vehicle vehicle){
    // Optional<ParkingSpot> result = spots.stream()
    // .filter(spot-> spot.isBooked()==false)
    // .findFirst();
    // if(result.isPresent()){
    // ParkingSpot spot = result.get();
    // spot.booked = true;
    // return true;
    // }
    // return false;
    // }
    //
    ParkingLot() {
        spots = new Vector<>();
    }

    void addSpot(ParkingSpot spot) {
        spots.addLast(spot);
    }

    int freeSpots() {
        int freeCount = spots.stream().reduce(0, (acc, spot) -> !spot.isBooked() ? acc + 1 : acc, Integer::sum);
        return freeCount;
    }

    int freeSpots(int _level) {
        long count = spots.stream()
                .filter(spot -> (spot.level == _level) && !spot.isBooked())
                .count();
        return (int) count;
    }

    ParkingSpot findFreeSpotByType(VehicleTypes t) {
        Optional<ParkingSpot> result = spots.stream()
                .filter(spot -> spot.spotType == t && !spot.isBooked())
                .findFirst();
        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    String park(Vehicle v) {

        if (v.parked) {
            return "Vechile is already Parked";
        }

        ParkingSpot foundSpot = this.findFreeSpotByType(v.type);

        if (foundSpot == null) {
            return "Can't park! No free spot found!";
        }

        Ticket ticket = new Ticket(foundSpot.id, v.id, foundSpot.level, v.type);
        foundSpot.book(ticket);
        v.park(ticket);

        return "Parked succesfully";
    }

}
