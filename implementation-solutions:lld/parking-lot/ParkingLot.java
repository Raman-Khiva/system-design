
import java.util.Optional;
import java.util.Vector;

public class ParkingLot{
   Vector<ParkingSpot> spots;


   boolean park(Vechile vechile){
       Optional<ParkingSpot> result = spots.stream()
                                           .filter(spot-> spot.book==false)
                                           .findFirst();
        if(result.isPresent()){
            return true;
        }
       return false;
   }
}
