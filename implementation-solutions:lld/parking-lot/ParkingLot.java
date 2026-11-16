
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;
import java.util.Vector;

public class ParkingLot {
    Vector<ParkingSpot> spots;

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

    ParkingSpot findFreeSpotBySize(Sizes sz) {
        spots.sort(Comparator.comparing(spot -> spot.capicity));
        Optional<ParkingSpot> result = spots.stream()
                .filter(spot -> (spot.capicity.compareTo(sz) >= 0) && !spot.isBooked())
                .findFirst();
        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    ParkingSpot getSpot(UUID id) {
        Optional<ParkingSpot> result = spots.stream()
                .filter(spot -> spot.id.equals(id))
                .findFirst();

        if (!result.isPresent()) {
            return null;
        }

        return result.get();
    }

}
